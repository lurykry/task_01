package edu.parammanagment.parammanagment.rest.controller;

import edu.parammanagment.parammanagment.domain.core.AbstractEntity;
import edu.parammanagment.parammanagment.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractControllerImpl<E extends AbstractEntity,
                                                S extends AbstractService<E>> {

    protected S service;

    @Autowired
    public AbstractControllerImpl(S service) {
        this.service = service;
    }
}