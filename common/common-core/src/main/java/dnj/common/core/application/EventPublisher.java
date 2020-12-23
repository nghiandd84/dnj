package dnj.common.core.application;

import dnj.common.core.domain.DomainEvent;

import java.util.List;

public interface EventPublisher {
    void publish(DomainEvent domainEvent);

    default void publish(List<DomainEvent> domainEvents) {
        domainEvents.forEach(this::publish);
    }
}
