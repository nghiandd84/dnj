package dnj.common.core.utils;

public interface DomainObjectMapper<D, T> {
    D toDomain(T object);

    T fromDomain(D object);
}
