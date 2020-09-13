package edu.parammanagment.parammanagment.rest.controller;

import edu.parammanagment.parammanagment.aspect.LoggingAnnotation;
import edu.parammanagment.parammanagment.domain.core.Parameter;
import edu.parammanagment.parammanagment.rest.exception.ModelNotFoundException;
import edu.parammanagment.parammanagment.rest.mapper.ModelAssembler;
import edu.parammanagment.parammanagment.rest.mapper.toentity.ParameterEntityMapper;
import edu.parammanagment.parammanagment.rest.mapper.tomodel.ParameterModelMapper;
import edu.parammanagment.parammanagment.rest.model.ParameterModel;
import edu.parammanagment.parammanagment.service.ParameterService;
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
 * Controller, handling {@link Parameter} requests.
 * @author Kirill Mansurov
 * @version 1.0
 */
@RestController
@RequestMapping("/parameter")
public class ParameterController extends AbstractControllerImpl<Parameter, ParameterModel, ParameterService> {

    private static final int DEFAULT_PAGE_NUMBER = 0;
    private static final int DEFAULT_PAGE_SIZE = 20;
    private final ModelAssembler<Parameter, ParameterModel,ParameterController> modelAssembler;

    @Autowired
    public ParameterController(ParameterService service,
                               @Qualifier("parameterModelAssembler") ModelAssembler<Parameter,
                                       ParameterModel, ParameterController> modelAssembler) {
        super(service);
        this.modelAssembler = modelAssembler;
    }

    @Override
    @GetMapping(value = "/{uuid}", produces = "application/json")
    public HttpEntity<ParameterModel> get(@PathVariable(value = "uuid") UUID uuid){
        Optional<Parameter> optionalParam = service.findById(uuid);

        return optionalParam.map(param -> {
            ParameterModel model = ParameterModelMapper.INSTANCE.map(param);
            return new ResponseEntity<>(model, HttpStatus.OK);
        }).orElseThrow(() -> new ModelNotFoundException(String.format("no parameter found for id: %s", uuid)));
    }

    @Override
    @PostMapping(value = "/save", consumes = "application/json")
    @LoggingAnnotation
    public void save(@RequestBody ParameterModel model) {
        Parameter parameter = ParameterEntityMapper.INSTANCE.map(model);
        service.save(parameter);
    }

    @Override
    @DeleteMapping(value = "/delete/{uuid}", consumes = "application/json")
    public void deleteById(@PathVariable("uuid") UUID uuid) {
        super.deleteById(uuid);
    }

        @GetMapping(value = "/all", produces = "application/json")
    public HttpEntity<PagedModel<ParameterModel>> getAllParameters(
            @PageableDefault(page = DEFAULT_PAGE_NUMBER, size = DEFAULT_PAGE_SIZE) Pageable pageRequest,
            PagedResourcesAssembler<Parameter> pagedResourcesAssembler){

        Page<Parameter> parameter = service.findAll(pageRequest);
        PagedModel<ParameterModel> model = pagedResourcesAssembler
                .toModel(parameter, modelAssembler);
        return new ResponseEntity<>(model, HttpStatus.OK);
    }

}