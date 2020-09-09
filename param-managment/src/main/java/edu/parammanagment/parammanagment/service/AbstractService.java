package edu.parammanagment.parammanagment.service;

import edu.parammanagment.parammanagment.domain.core.AbstractEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AbstractService<E extends AbstractEntity> {

    void save(E entity);
    void save(E entity, String... refs);
    Optional<E> findById(UUID id);
    List<E> findAll();
    Page<E> findAll(Pageable pageable);
}