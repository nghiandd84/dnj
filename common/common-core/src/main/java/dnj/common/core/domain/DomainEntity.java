package dnj.common.core.domain;

import java.util.ArrayList;
import java.util.List;

public interface DomainEntity<T extends DomainEntityId> {
    List<DomainEvent> occurredEvents = new ArrayList<DomainEvent>();

    T getId();

    default List<DomainEvent> getOccurredEvents() {
        List<DomainEvent> copy = new ArrayList<DomainEvent>(occurredEvents);
        occurredEvents.clear();
        return copy;
    }

    default void raise(DomainEvent domainEvent) {
        occurredEvents.add(domainEvent);
    }
}
