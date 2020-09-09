package edu.parammanagment.parammanagment.rest.mapper.tomodel;

import edu.parammanagment.parammanagment.domain.core.Parameter;
import edu.parammanagment.parammanagment.rest.controller.ParameterController;
import edu.parammanagment.parammanagment.rest.controller.RelevantDataController;
import edu.parammanagment.parammanagment.rest.mapper.ModelMapper;
import edu.parammanagment.parammanagment.rest.model.ParameterModel;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.hateoas.Link;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Mapper
public interface ParameterModelMapper extends ModelMapper<Parameter, ParameterModel> {

    ParameterModelMapper INSTANCE = Mappers.getMapper(ParameterModelMapper.class);

    ParameterModel map(Parameter parameter);

    @AfterMapping
    default void addLinks(@MappingTarget ParameterModel model, Parameter entity) {
        Link selfLink = linkTo(methodOn(ParameterController.class).getParameter(entity.getUuid()))
                .withSelfRel();
        Link relevantDataLink = linkTo(methodOn(RelevantDataController.class)
                .getRelevantData(entity.getRelevantData().getUuid()))
                .withRel("relevant_data");
            model.add(selfLink, relevantDataLink);
    }
}
