package edu.parammanagment.parammanagment.service;

import edu.parammanagment.parammanagment.domain.core.AbstractEntity;
import edu.parammanagment.parammanagment.repository.AbstractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Generic service-layer abstract class, defining some of shared method.
 * @param <E> is an entity, extending {@link AbstractEntity}.
 * @param <R> is a repository, extending {@link AbstractRepository}.
 * @author Kirill Mansurov
 * @version 1.0
 */
@Transactional
public abstract class AbstractServiceImpl<E extends AbstractEntity, R extends AbstractRepository<E>> implements AbstractService<E>{

    protected final R repository;

    @Autowired
    public AbstractServiceImpl(R repository) {
        this.repository = repository;
    }


    @Override
    public void save(E entity) {
        repository.save(entity);
    }

    @Override
    public void save(E entity, String... refs){}

    @Transactional(readOnly = true)
    @Override
    public Optional<E> findById(UUID id) {
        return repository.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<E> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<E> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public void deleteById(UUID id){
        repository.deleteById(id);
    }
}