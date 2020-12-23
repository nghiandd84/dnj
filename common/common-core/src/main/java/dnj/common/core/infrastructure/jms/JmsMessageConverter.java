package dnj.common.core.infrastructure.jms;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import static dnj.common.core.infrastructure.jms.TypeIdMappingFactory.getTypeIdMapping;

@Slf4j
public class JmsMessageConverter implements MessageConverter {
	private final MappingJackson2MessageConverter jacksonMessageConverter = new MappingJackson2MessageConverter();

	public JmsMessageConverter() {
		jacksonMessageConverter.setTargetType(MessageType.TEXT);
		jacksonMessageConverter.setTypeIdPropertyName("_type");
		jacksonMessageConverter.setTypeIdMappings(getTypeIdMapping());
	}

	@Override
	public Message toMessage(Object object, Session session) throws JMSException, MessageConversionException {
		log.info("toMessage [object={}] and [session={}]", object, session);
		return jacksonMessageConverter.toMessage(object, session);
	}

	@Override
	public Object fromMessage(Message message) throws JMSException, MessageConversionException {
		log.info("fromMessage [message={}]", message);
		return jacksonMessageConverter.fromMessage(message);
	}
}
