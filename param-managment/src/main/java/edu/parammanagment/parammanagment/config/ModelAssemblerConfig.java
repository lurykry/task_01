package edu.parammanagment.parammanagment.config;

import edu.parammanagment.parammanagment.domain.core.DataRecord;
import edu.parammanagment.parammanagment.rest.controller.DataRecordController;
import edu.parammanagment.parammanagment.rest.mapper.ModelAssembler;
import edu.parammanagment.parammanagment.rest.mapper.tomodel.DataRecordModelMapper;
import edu.parammanagment.parammanagment.rest.model.DataRecordModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelAssemblerConfig {

    @Bean
    ModelAssembler<DataRecord, DataRecordModel, DataRecordController> relevantDataModelAssembler(){
        return new ModelAssembler<DataRecord, DataRecordModel, DataRecordController>(DataRecordController.class,
                DataRecordModel.class, DataRecordModelMapper.INSTANCE);
    }
}
