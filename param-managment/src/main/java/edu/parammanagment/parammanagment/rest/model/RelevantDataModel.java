package edu.parammanagment.parammanagment.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import edu.parammanagment.parammanagment.domain.helpers.ParameterTypeAndValue;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.hateoas.server.core.Relation;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Relation(value = "current_data", collectionRelation = "current_data")
@JsonPropertyOrder({ "id", "recordDate", "createdAt", "modifiedAt", "parameterTypeAndValue", "paramUUID"})
public class RelevantDataModel extends AbstractModel{

    private LocalDateTime recordDate;
    @JsonProperty("value")
    private ParameterTypeAndValue parameterTypeAndValue;
    @JsonProperty("parameter")
    private String paramRef;

}
