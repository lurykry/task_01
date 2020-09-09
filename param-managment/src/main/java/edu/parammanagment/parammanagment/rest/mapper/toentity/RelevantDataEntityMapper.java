package edu.parammanagment.parammanagment.rest.mapper.toentity;

import edu.parammanagment.parammanagment.domain.core.RelevantData;
import edu.parammanagment.parammanagment.rest.mapper.EntityMapper;
import edu.parammanagment.parammanagment.rest.model.RelevantDataModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RelevantDataEntityMapper extends EntityMapper<RelevantDataModel, RelevantData> {

    RelevantDataEntityMapper INSTANCE = Mappers.getMapper(RelevantDataEntityMapper.class);

    @Mapping(target = "parameter", ignore = true)
    RelevantData map(RelevantDataModel relevantDataModel);
}
