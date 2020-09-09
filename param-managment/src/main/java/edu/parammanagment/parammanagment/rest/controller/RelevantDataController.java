package edu.parammanagment.parammanagment.rest.controller;

import edu.parammanagment.parammanagment.aspect.LoggingAnnotation;
import edu.parammanagment.parammanagment.domain.core.RelevantData;
import edu.parammanagment.parammanagment.rest.exception.ModelNotFoundException;
import edu.parammanagment.parammanagment.rest.mapper.toentity.RelevantDataEntityMapper;
import edu.parammanagment.parammanagment.rest.mapper.tomodel.RelevantDataModelMapper;
import edu.parammanagment.parammanagment.rest.model.RelevantDataModel;
import edu.parammanagment.parammanagment.service.RelevantDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/relevantdata")
public class RelevantDataController extends AbstractControllerImpl<RelevantData, RelevantDataService> {

    @Autowired
    public RelevantDataController(RelevantDataService service) {
        super(service);
    }

    @GetMapping(value = "/{uuid}", produces = "application/json")
    public HttpEntity<RelevantDataModel> getRelevantData(@PathVariable(value = "uuid") UUID uuid){
        Optional<RelevantData> optionalData = service.findById(uuid);

        return optionalData.map(data -> {
            RelevantDataModel model = RelevantDataModelMapper.INSTANCE.map(data);
            return new ResponseEntity<>(model, HttpStatus.OK);
        }).orElseThrow(() -> new ModelNotFoundException(String.format("no relevant data found for id: %s", uuid)));
    }

    @PostMapping(value = "/save", consumes = "application/json")
    @LoggingAnnotation
    public void saveRelevantData(@RequestBody RelevantDataModel model) {
        RelevantData relevantData = RelevantDataEntityMapper.INSTANCE.map(model);
        service.save(relevantData, model.getParamRef());
    }
}
