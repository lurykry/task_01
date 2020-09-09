package edu.parammanagment.parammanagment.rest.controller;

import edu.parammanagment.parammanagment.aspect.LoggingAnnotation;
import edu.parammanagment.parammanagment.domain.core.Parameter;
import edu.parammanagment.parammanagment.rest.exception.ModelNotFoundException;
import edu.parammanagment.parammanagment.rest.mapper.toentity.ParameterEntityMapper;
import edu.parammanagment.parammanagment.rest.mapper.tomodel.ParameterModelMapper;
import edu.parammanagment.parammanagment.rest.model.ParameterModel;
import edu.parammanagment.parammanagment.service.ParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/parameter")
public class ParameterController extends AbstractControllerImpl<Parameter, ParameterService> {

    @Autowired
    public ParameterController(ParameterService service) {
        super(service);
    }

    @GetMapping(value = "/{uuid}", produces = "application/json")
    public HttpEntity<ParameterModel> getParameter(@PathVariable(value = "uuid") UUID uuid){
        Optional<Parameter> optionalParam = service.findById(uuid);

        return optionalParam.map(param -> {
            ParameterModel model = ParameterModelMapper.INSTANCE.map(param);
            return new ResponseEntity<>(model, HttpStatus.OK);
        }).orElseThrow(() -> new ModelNotFoundException(String.format("no parameter found for id: %s", uuid)));
    }

    @PostMapping(value = "/save", consumes = "application/json")
    @LoggingAnnotation
    public void saveParameter(@RequestBody ParameterModel model) {
        Parameter parameter = ParameterEntityMapper.INSTANCE.map(model);
        service.save(parameter);
    }
}