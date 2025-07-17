package de.localrag.backend.api;

public interface DTOConverter<E, D> {
    D toDTO(E entity);
    E toEntity(D dto);//TODO maybe only one way -> Login/Register can get their own...
}
