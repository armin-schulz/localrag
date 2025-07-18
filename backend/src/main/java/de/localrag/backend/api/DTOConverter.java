package de.localrag.backend.api;

public interface DTOConverter<E, D> {
    D toDTO(E entity);
}
