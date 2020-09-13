package edu.parammanagment.parammanagment.domain.helpers;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

@Getter
@Setter
@ToString
public class ParameterTypeAndValue implements Serializable {

    private String paramType;
    @Enumerated(EnumType.STRING)
    private String paramValue;

    public ParameterTypeAndValue() {
    }

    public ParameterTypeAndValue(String paramType, String paramValue) {
        this.paramType = paramType;
        this.paramValue = paramValue;
    }
}
