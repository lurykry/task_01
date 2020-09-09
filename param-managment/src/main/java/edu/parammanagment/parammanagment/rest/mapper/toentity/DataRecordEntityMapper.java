package edu.parammanagment.parammanagment.rest.mapper.toentity;

import edu.parammanagment.parammanagment.domain.core.DataRecord;
import edu.parammanagment.parammanagment.rest.mapper.EntityMapper;
import edu.parammanagment.parammanagment.rest.model.DataRecordModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DataRecordEntityMapper extends EntityMapper<DataRecordModel, DataRecord> {

    DataRecordEntityMapper INSTANCE = Mappers.getMapper(DataRecordEntityMapper.class);

    @Mapping(target = "parameter",ignore = true)
    @Mapping(target = "relevantData",ignore = true)
    DataRecord map(DataRecordModel dataRecordModel);
}
