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
@Relation(value = "data_record", collectionRelation = "data_records")
@JsonPropertyOrder({ "id", "recordDate", "createdAt", "modifiedAt", "parameterTypeAndValue" })
public class DataRecordModel extends AbstractModel{

    private LocalDateTime recordDate;
    @JsonProperty("value")
    private ParameterTypeAndValue parameterTypeAndValue;
    @JsonProperty("parameter")
    private String paramRef;
    @JsonProperty("relevant_data")
    private String relevantDataRef;

}
