package dnj.common.core.application;

public interface MessageRepository {
    Message findUnprocessedMessage(String listenerName);

    void save(Message message);
}
