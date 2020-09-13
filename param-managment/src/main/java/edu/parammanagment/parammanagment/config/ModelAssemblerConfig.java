package edu.parammanagment.parammanagment.config;

import edu.parammanagment.parammanagment.domain.core.DataRecord;
import edu.parammanagment.parammanagment.domain.core.Parameter;
import edu.parammanagment.parammanagment.domain.core.RelevantData;
import edu.parammanagment.parammanagment.rest.controller.DataRecordController;
import edu.parammanagment.parammanagment.rest.controller.ParameterController;
import edu.parammanagment.parammanagment.rest.controller.RelevantDataController;
import edu.parammanagment.parammanagment.rest.mapper.ModelAssembler;
import edu.parammanagment.parammanagment.rest.mapper.tomodel.DataRecordModelMapper;
import edu.parammanagment.parammanagment.rest.mapper.tomodel.ParameterModelMapper;
import edu.parammanagment.parammanagment.rest.mapper.tomodel.RelevantDataModelMapper;
import edu.parammanagment.parammanagment.rest.model.DataRecordModel;
import edu.parammanagment.parammanagment.rest.model.ParameterModel;
import edu.parammanagment.parammanagment.rest.model.RelevantDataModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelAssemblerConfig {

    @Bean
    ModelAssembler<DataRecord, DataRecordModel, DataRecordController> dataRecordModelAssembler(){
        return new ModelAssembler<>(DataRecordController.class,
                DataRecordModel.class, DataRecordModelMapper.INSTANCE);
    }

    @Bean
    ModelAssembler<Parameter, ParameterModel, ParameterController> parameterModelAssembler(){
        return new ModelAssembler<>(ParameterController.class,
                ParameterModel.class, ParameterModelMapper.INSTANCE);
    }

    @Bean
    ModelAssembler<RelevantData, RelevantDataModel, RelevantDataController> relevantDataModelAssembler(){
        return new ModelAssembler<>(RelevantDataController.class,
                RelevantDataModel.class, RelevantDataModelMapper.INSTANCE);
    }

}
