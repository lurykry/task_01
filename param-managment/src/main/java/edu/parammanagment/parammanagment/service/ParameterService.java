package edu.parammanagment.parammanagment.service;

import edu.parammanagment.parammanagment.domain.core.Parameter;
import edu.parammanagment.parammanagment.repository.ParameterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Kirill Mansurov
 * @version 1.0
 */
@Service
public class ParameterService extends AbstractServiceImpl<Parameter, ParameterRepository> {

    @Autowired
    public ParameterService(ParameterRepository repository) {
        super(repository);
    }

}