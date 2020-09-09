package edu.parammanagment.parammanagment.service;

import edu.parammanagment.parammanagment.domain.core.DataRecord;
import edu.parammanagment.parammanagment.domain.core.Parameter;
import edu.parammanagment.parammanagment.domain.core.RelevantData;
import edu.parammanagment.parammanagment.domain.helpers.ParameterTypeAndValue;
import edu.parammanagment.parammanagment.repository.RelevantDataRepository;
import edu.parammanagment.parammanagment.rest.exception.InvalidURIInputException;
import edu.parammanagment.parammanagment.rest.exception.ModelNotFoundException;
import edu.parammanagment.parammanagment.util.uuidextractor.UUIDExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class RelevantDataService extends AbstractServiceImpl<RelevantData, RelevantDataRepository> {

    private final AbstractService<Parameter> parameterService;

    @Autowired
    public RelevantDataService(RelevantDataRepository repository, AbstractService<Parameter> parameterService) {
        super(repository);
        this.parameterService = parameterService;
    }

    @Override
    public void save(RelevantData entity, String... refs) {
        if(refs.length != 1 || refs[0] == null ||refs[0].equals(""))
            throw new InvalidURIInputException(String.format("invalid uri: %s", refs[0]));

        UUID paramUUID = UUID.fromString(UUIDExtractor.extractUUID(refs[0]).orElseThrow(() ->
                new InvalidURIInputException(String.format("invalid uri: %s", refs[0]))));
        Optional<Parameter> optionalParam = parameterService.findById(paramUUID);
        Parameter parameter = optionalParam.orElseThrow(() -> new ModelNotFoundException(String.format("no parameter found for id: %s", paramUUID)));

        entity.setParameter(parameter);
        entity.addDataRecord(new DataRecord(entity.getRecordDate(), entity.getParameterTypeAndValue(), parameter));
        repository.save(entity);
    }

    @Transactional
    public void updateRelevantData(LocalDateTime recordDate, ParameterTypeAndValue parameterTypeAndValue, UUID uuid){
        repository.updateRelevantData(recordDate, parameterTypeAndValue, uuid);
    }
}