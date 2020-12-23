package dnj.common.core.application;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = "id")
public class Message {
    @NonNull
    private String id;

    @NonNull
    private String type;

    @NonNull
    private String content;

    @NonNull
    private String listener;

    @NonNull
    private int executionCount;

    @NonNull
    private boolean success;

    private String errorMessage;

    public static Message createNew(String id, String type, String content, String listener) {
        return Message.builder()
                .id(id)
                .type(type)
                .content(content)
                .listener(listener)
                .success(false)
                .executionCount(0)
                .build();
    }

    public void increaseExecutionCount() {
        executionCount++;
        log.trace("Message about to be executed. [listener={}, event={}, executionCount={}]", listener, type, executionCount);
    }



    public void succeeded() {
        this.success = true;
        this.errorMessage = null;
        log.trace("Message handled successfully. [listener={}, event={}]", listener, type);
    }

    public void failed(Exception exception) {
        this.success = false;
        this.errorMessage = exception.getMessage();
        log.warn("Failed to handle message. [listener={}, event={}]", listener, type, exception);
    }

}
