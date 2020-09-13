package edu.parammanagment.parammanagment.service;

import edu.parammanagment.parammanagment.domain.core.DataRecord;
import edu.parammanagment.parammanagment.domain.core.Parameter;
import edu.parammanagment.parammanagment.domain.core.RelevantData;
import edu.parammanagment.parammanagment.repository.DataRecordRepository;
import edu.parammanagment.parammanagment.rest.exception.InvalidURIInputException;
import edu.parammanagment.parammanagment.rest.exception.ModelNotFoundException;
import edu.parammanagment.parammanagment.util.uuidextractor.UUIDExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Kirill Mansurov
 * @version 1.0
 */
@Service
public class DataRecordService extends AbstractServiceImpl<DataRecord, DataRecordRepository> {

    private final AbstractService<Parameter> parameterService;
    private final AbstractService<RelevantData> relevantDataService;

    @Autowired
    public DataRecordService(DataRecordRepository repository, AbstractService<Parameter> parameterService, AbstractService<RelevantData> relevantDataService) {
        super(repository);
        this.parameterService = parameterService;
        this.relevantDataService = relevantDataService;
    }

    @Override
    public void save(DataRecord entity, String... refs) {
        if(refs.length != 2 ||
           refs[0] == null || refs[0].equals("") ||
           refs[1] == null || refs[1].equals(""))
            throw new InvalidURIInputException(String.format("invalid uri(s): %s, _%s",refs[0], refs[1]));

        UUID paramUUID = UUID.fromString(UUIDExtractor.extractUUID(refs[0]).orElseThrow(() ->
                new InvalidURIInputException(String.format("invalid uri: %s", refs[0]))));
        UUID relevantDataUUID = UUID.fromString(UUIDExtractor.extractUUID(refs[1]).orElseThrow(() ->
                new InvalidURIInputException(String.format("invalid uri: %s", refs[1]))));

        Optional<Parameter> optionalParam = parameterService.findById(paramUUID);
        Parameter parameter = optionalParam.orElseThrow(() -> new ModelNotFoundException(String.format("no parameter found for id: %s", paramUUID)));

        Optional<RelevantData> optionalRelevantData = relevantDataService.findById(relevantDataUUID);
        RelevantData relevantData = optionalRelevantData.orElseThrow(() -> new ModelNotFoundException(String.format("no relevant found for id: %s", relevantDataUUID)));

        boolean isRelevantDataUpdated = isRelevantDataUpdated(entity, relevantData);
        if(!isRelevantDataUpdated)
            ((RelevantDataService)relevantDataService).updateRelevantData(relevantDataUUID, entity.getRecordDate(),
                    entity.getParameterTypeAndValue());

        entity.setParameter(parameter);
        entity.setRelevantData(relevantData);
        repository.save(entity);
    }

    private boolean isRelevantDataUpdated(DataRecord dataRecord, RelevantData relevantData){
        LocalDateTime dataRecordDateTime = dataRecord.getRecordDate();
        LocalDateTime relevantDataDateTime = relevantData.getRecordDate();

        return !dataRecordDateTime.isAfter(relevantDataDateTime);
    }
}
