package edu.parammanagment.parammanagment.service;

import edu.parammanagment.parammanagment.domain.core.AbstractEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Generic service-layer interface,declaring shared methods.
 * @param <E> is an entity, extending {@link AbstractEntity}.
 * @author Kirill Mansurov
 * @version 1.0
 */
public interface AbstractService<E extends AbstractEntity> {

    void save(E entity);
    void save(E entity, String... refs);
    Optional<E> findById(UUID id);
    List<E> findAll();
    Page<E> findAll(Pageable pageable);
    void deleteById(UUID id);
}