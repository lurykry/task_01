package edu.parammanagment.parammanagment.rest.controller;

import edu.parammanagment.parammanagment.rest.model.AbstractModel;
import org.springframework.http.HttpEntity;

import java.util.UUID;

public interface AbstractController<M extends AbstractModel> {

    void save(M model);
    HttpEntity<M> get(UUID uuid);
    void update(M model);
    void deleteById(UUID uuid);
}
