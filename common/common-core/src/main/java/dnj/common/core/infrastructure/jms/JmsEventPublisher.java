package dnj.common.core.infrastructure.jms;

import dnj.common.core.application.EventPublisher;
import dnj.common.core.domain.DomainEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.jms.core.JmsTemplate;

import javax.jms.Destination;

@Slf4j
@AllArgsConstructor
public class JmsEventPublisher implements EventPublisher {
    private final JmsTemplate jmsTemplate;
    private final Destination destination;

    @Override
    public void publish(DomainEvent event) {
        jmsTemplate.convertAndSend(destination, event);
        log.info("Sent event. [event={}, destination={}]", event);
    }
}
