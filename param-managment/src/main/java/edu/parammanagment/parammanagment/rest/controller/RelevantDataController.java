package edu.parammanagment.parammanagment.rest.controller;

import edu.parammanagment.parammanagment.aspect.LoggingAnnotation;
import edu.parammanagment.parammanagment.domain.core.RelevantData;
import edu.parammanagment.parammanagment.rest.exception.ModelNotFoundException;
import edu.parammanagment.parammanagment.rest.mapper.ModelAssembler;
import edu.parammanagment.parammanagment.rest.mapper.toentity.RelevantDataEntityMapper;
import edu.parammanagment.parammanagment.rest.mapper.tomodel.RelevantDataModelMapper;
import edu.parammanagment.parammanagment.rest.model.RelevantDataModel;
import edu.parammanagment.parammanagment.service.RelevantDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

/**
 * Controller, handling {@link RelevantData} requests.
 * @author Kirill Mansurov
 * @version 1.0
 */
@RestController
@RequestMapping("/relevantdata")
public class RelevantDataController extends AbstractControllerImpl<RelevantData, RelevantDataModel, RelevantDataService> {

    private static final int DEFAULT_PAGE_NUMBER = 0;
    private static final int DEFAULT_PAGE_SIZE = 20;
    private final ModelAssembler<RelevantData,RelevantDataModel,RelevantDataController> modelAssembler;

    @Autowired
    public RelevantDataController(RelevantDataService service,
                                  @Qualifier("relevantDataModelAssembler") ModelAssembler<RelevantData,
                                          RelevantDataModel, RelevantDataController> modelAssembler) {
        super(service);
        this.modelAssembler = modelAssembler;
    }

    @Override
    @GetMapping(value = "/{uuid}", produces = "application/json")
    public HttpEntity<RelevantDataModel> get(@PathVariable(value = "uuid") UUID uuid){
        Optional<RelevantData> optionalData = service.findById(uuid);

        return optionalData.map(data -> {
            RelevantDataModel model = RelevantDataModelMapper.INSTANCE.map(data);
            return new ResponseEntity<>(model, HttpStatus.OK);
        }).orElseThrow(() -> new ModelNotFoundException(String.format("no relevant data found for id: %s", uuid)));
    }

    @Override
    @PostMapping(value = "/save", consumes = "application/json")
    @LoggingAnnotation
    public void save(@RequestBody RelevantDataModel model) {
        RelevantData relevantData = RelevantDataEntityMapper.INSTANCE.map(model);
        service.save(relevantData, model.getParamRef());
    }

    @Override
    @PutMapping(value = "/update", consumes = "application/json")
    public void update(RelevantDataModel model) {
       RelevantData relevantData = RelevantDataEntityMapper.INSTANCE.map(model);
       service.updateRelevantData(relevantData.getUuid(),relevantData.getRecordDate(),
               relevantData.getParameterTypeAndValue());
    }

    @Override
    @DeleteMapping(value = "/delete/{uuid}", consumes = "application/json")
    public void deleteById(@PathVariable("uuid") UUID uuid) {
        super.deleteById(uuid);
    }

    @GetMapping(value = "/all", produces = "application/json")
    public HttpEntity<PagedModel<RelevantDataModel>> getAllRelevantData(
            @PageableDefault(page = DEFAULT_PAGE_NUMBER, size = DEFAULT_PAGE_SIZE, sort = "uuid") Pageable pageRequest,
            PagedResourcesAssembler<RelevantData> pagedResourcesAssembler){

        Page<RelevantData> data = service.findAll(pageRequest);
        PagedModel<RelevantDataModel> model = pagedResourcesAssembler
                .toModel(data, modelAssembler);
        return new ResponseEntity<>(model, HttpStatus.OK);
    }
}
