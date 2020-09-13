package edu.parammanagment.parammanagment.rest.controller;

import edu.parammanagment.parammanagment.domain.core.AbstractEntity;
import edu.parammanagment.parammanagment.domain.core.Parameter;
import edu.parammanagment.parammanagment.rest.exception.ModelNotFoundException;
import edu.parammanagment.parammanagment.rest.mapper.ModelAssembler;
import edu.parammanagment.parammanagment.rest.mapper.tomodel.ParameterModelMapper;
import edu.parammanagment.parammanagment.rest.model.AbstractModel;
import edu.parammanagment.parammanagment.rest.model.ParameterModel;
import edu.parammanagment.parammanagment.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;
import java.util.UUID;

/**
 * Abstract controller, providing a shared service for its extenders.
 * @param <E> is an entity, extending {@link AbstractEntity}.
 * @param <S> is a service, extending {@link AbstractService}.
 * @param <M> is a model, extending {@link AbstractModel}.
 * @author Kirill Mansurov
 * @version 1.0
 */
public abstract class AbstractControllerImpl<E extends AbstractEntity, M extends AbstractModel,
                                                S extends AbstractService<E>> implements AbstractController<M>{

    protected S service;

    @Autowired
    public AbstractControllerImpl(S service) {
        this.service = service;
    }

    @Override
    public HttpEntity<M> get(UUID uuid) {
        return null;
    }

    @Override
    public void save(M model) {

    }

    @Override
    public void update(M model) {

    }

    @Override
    public void deleteById(UUID uuid) {
        service.deleteById(uuid);
    }
}