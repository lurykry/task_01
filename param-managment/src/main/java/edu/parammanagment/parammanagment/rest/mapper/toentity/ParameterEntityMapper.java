package edu.parammanagment.parammanagment.rest.mapper.toentity;

import edu.parammanagment.parammanagment.domain.core.Parameter;
import edu.parammanagment.parammanagment.rest.mapper.EntityMapper;
import edu.parammanagment.parammanagment.rest.model.ParameterModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ParameterEntityMapper extends EntityMapper<ParameterModel, Parameter> {

    ParameterEntityMapper INSTANCE = Mappers.getMapper(ParameterEntityMapper.class);

    @Mapping(target = "relevantData", ignore = true)
    Parameter map(ParameterModel parameterModel);

}
