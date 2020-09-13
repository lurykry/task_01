package edu.parammanagment.parammanagment.rest.controller;

import edu.parammanagment.parammanagment.aspect.LoggingAnnotation;
import edu.parammanagment.parammanagment.domain.core.DataRecord;
import edu.parammanagment.parammanagment.rest.exception.ModelNotFoundException;
import edu.parammanagment.parammanagment.rest.mapper.ModelAssembler;
import edu.parammanagment.parammanagment.rest.mapper.toentity.DataRecordEntityMapper;
import edu.parammanagment.parammanagment.rest.mapper.tomodel.DataRecordModelMapper;
import edu.parammanagment.parammanagment.rest.model.DataRecordModel;
import edu.parammanagment.parammanagment.service.DataRecordService;
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
 * Controller, handling {@link DataRecord} requests.
 * @author Kirill Mansurov
 * @version 1.0
 */
@RestController
@RequestMapping("/history")
public class DataRecordController extends AbstractControllerImpl<DataRecord, DataRecordModel, DataRecordService> {

    private static final int DEFAULT_PAGE_NUMBER = 0;
    private static final int DEFAULT_PAGE_SIZE = 20;
    private final ModelAssembler<DataRecord,DataRecordModel,DataRecordController> modelAssembler;

    public DataRecordController(DataRecordService service,
                                @Qualifier("dataRecordModelAssembler")ModelAssembler<DataRecord,
                                        DataRecordModel, DataRecordController> modelAssembler) {
        super(service);
        this.modelAssembler = modelAssembler;
    }

    @Override
    @GetMapping(value = "/{uuid}", produces = "application/json")
    public HttpEntity<DataRecordModel> get(@PathVariable(value = "uuid") UUID uuid){
        Optional<DataRecord> optionalRecord = service.findById(uuid);

        return optionalRecord.map(record -> {
            DataRecordModel model = DataRecordModelMapper.INSTANCE.map(record);
            return new ResponseEntity<>(model, HttpStatus.OK);
        }).orElseThrow(() -> new ModelNotFoundException("no record found"));
    }

    @Override
    @PostMapping(value = "/save", consumes = "application/json")
    @LoggingAnnotation
    public void save(@RequestBody DataRecordModel model) {
        DataRecord dataRecord = DataRecordEntityMapper.INSTANCE.map(model);
        service.save(dataRecord, model.getParamRef(), model.getRelevantDataRef());
    }

    @Override
    @DeleteMapping(value = "/delete/{uuid}", consumes = "application/json")
    public void deleteById(@PathVariable("uuid") UUID uuid) {
        super.deleteById(uuid);
    }

    @GetMapping(value = "/all", produces = "application/json")
    public HttpEntity<PagedModel<DataRecordModel>> getRecords(
            @PageableDefault(page = DEFAULT_PAGE_NUMBER, size = DEFAULT_PAGE_SIZE, sort = "uuid") Pageable pageRequest,
            PagedResourcesAssembler<DataRecord> pagedResourcesAssembler){

        Page<DataRecord> records = service.findAll(pageRequest);
        PagedModel<DataRecordModel> model = pagedResourcesAssembler
                .toModel(records, modelAssembler);
        return new ResponseEntity<>(model, HttpStatus.OK);
    }
}
