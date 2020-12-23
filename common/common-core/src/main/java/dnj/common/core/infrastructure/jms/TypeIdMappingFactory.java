package dnj.common.core.infrastructure.jms;

import dnj.common.core.domain.DomainEvent;
import dnj.common.core.utils.SubclassFinder;

import java.util.Map;
import java.util.stream.Collectors;

public class TypeIdMappingFactory {
	public static Map<String, Class<?>> getTypeIdMapping() {
		return SubclassFinder.findAllSubtypes(DomainEvent.class, "com.dn").stream()
				.collect(Collectors.toMap(clazz -> clazz.getSimpleName(), clazz -> clazz));
	}
}
