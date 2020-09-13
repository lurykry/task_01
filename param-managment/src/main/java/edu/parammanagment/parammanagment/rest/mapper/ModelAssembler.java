package edu.parammanagment.parammanagment.rest.mapper;

import edu.parammanagment.parammanagment.domain.core.AbstractEntity;
import edu.parammanagment.parammanagment.rest.model.AbstractModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

public class ModelAssembler<E extends AbstractEntity,R extends AbstractModel,
        C> extends RepresentationModelAssemblerSupport<E,R> {

    private final ModelMapper<E, R> modelMapper;

    public ModelAssembler(Class<C> controllerClass, Class<R> modelClass, ModelMapper<E, R> modelMapper) {
        super(controllerClass, modelClass);
        this.modelMapper = modelMapper;
    }

    @Override
    public R toModel(E entity) {
        return modelMapper.map(entity);
    }
}
