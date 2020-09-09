package edu.parammanagment.parammanagment.rest.mapper.tomodel;

import edu.parammanagment.parammanagment.domain.core.DataRecord;
import edu.parammanagment.parammanagment.rest.controller.RelevantDataController;
import edu.parammanagment.parammanagment.rest.controller.DataRecordController;
import edu.parammanagment.parammanagment.rest.controller.ParameterController;
import edu.parammanagment.parammanagment.rest.mapper.ModelMapper;
import edu.parammanagment.parammanagment.rest.model.DataRecordModel;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.hateoas.Link;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Mapper
public interface DataRecordModelMapper extends ModelMapper<DataRecord, DataRecordModel> {

    DataRecordModelMapper INSTANCE = Mappers.getMapper(DataRecordModelMapper.class);

    @Mapping(target = "paramRef", ignore = true)
    @Mapping(target = "relevantDataRef", ignore = true)
    DataRecordModel map(DataRecord dataRecord);

    @AfterMapping
    default void addLinks(@MappingTarget DataRecordModel model, DataRecord entity) {
        Link selfLink = linkTo(methodOn(DataRecordController.class).getRecord(entity.getUuid()))
                .withSelfRel();
        Link paramLink = linkTo(methodOn(ParameterController.class).getParameter(entity.getParameter().getUuid()))
                .withRel("parameter");
        Link relevantDataLink = linkTo(methodOn(RelevantDataController.class).getRelevantData(entity.getRelevantData().getUuid()))
                .withRel("relevant data");
        model.add(selfLink, paramLink, relevantDataLink);
    }
}
