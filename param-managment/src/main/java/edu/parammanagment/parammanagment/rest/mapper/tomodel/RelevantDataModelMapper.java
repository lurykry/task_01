package edu.parammanagment.parammanagment.rest.mapper.tomodel;

import edu.parammanagment.parammanagment.domain.core.RelevantData;
import edu.parammanagment.parammanagment.rest.controller.DataRecordController;
import edu.parammanagment.parammanagment.rest.controller.RelevantDataController;
import edu.parammanagment.parammanagment.rest.controller.ParameterController;
import edu.parammanagment.parammanagment.rest.mapper.ModelMapper;
import edu.parammanagment.parammanagment.rest.model.RelevantDataModel;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.hateoas.Link;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Mapper
public interface RelevantDataModelMapper extends ModelMapper<RelevantData, RelevantDataModel> {

    RelevantDataModelMapper INSTANCE = Mappers.getMapper(RelevantDataModelMapper.class);

    @Mapping(target = "paramRef", ignore = true)
    RelevantDataModel map(RelevantData relevantData);

    @AfterMapping
    default void addLinks(@MappingTarget RelevantDataModel model, RelevantData entity) {
        Link selfLink = linkTo(methodOn(RelevantDataController.class).getRelevantData(entity.getUuid()))
                .withSelfRel();
        Link paramLink = linkTo(methodOn(ParameterController.class).getParameter(entity.getParameter().getUuid()))
                .withRel("parameter");
        entity.getDataRecords().forEach(
                record -> model.add(linkTo(methodOn(DataRecordController.class).getRecord(record.getUuid()))
                        .withRel("records"))
        );
        model.add(selfLink, paramLink);
    }
}
