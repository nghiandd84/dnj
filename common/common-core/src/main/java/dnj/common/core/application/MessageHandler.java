package dnj.common.core.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.lang.reflect.ParameterizedType;

import org.springframework.scheduling.annotation.Scheduled;

@AllArgsConstructor
@NoArgsConstructor
public abstract class MessageHandler<T> {
    protected MessageRepository messageRepository;
    protected ObjectMapper objectMapper;

    public boolean accepts(String type) {
        return getAcceptedType().equals(type);
    }


    private String getAcceptedType() {
        return getMessageClass().getSimpleName();
    }

    public String getName() {
        return getClass().getSimpleName();
    }

    public abstract void onMessage(T message);

    @SneakyThrows
    @Scheduled(fixedDelay = 100)
    public void checkForMessages() {
        Message message = messageRepository.findUnprocessedMessage(getName());
        if (message != null) {
            Class<T> eventClass = getMessageClass();
            message.increaseExecutionCount();
            try {
                String content = message.getContent();
                T eventObj = objectMapper.readValue(content, eventClass);
                onMessage(eventObj);
                message.succeeded();
            } catch (Exception e) {
                message.failed(e);
            }
            messageRepository.save(message);
        }
    }


    private Class<T> getMessageClass() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

}
