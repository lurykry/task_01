package edu.parammanagment.parammanagment.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import edu.parammanagment.parammanagment.domain.helpers.DataType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.hateoas.server.core.Relation;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Relation(value = "parameter", collectionRelation = "parameters")
@JsonPropertyOrder({ "id", "paramName", "paramCode", "paramDescription", "dataType" ,"createdAt" })
public class ParameterModel extends AbstractModel{

    @JsonProperty("name")
    private String paramName;
    @JsonProperty("code")
    private String paramCode;
    @JsonProperty("description")
    private String paramDescription;
    @JsonProperty("type")
    private DataType dataType;

    @Override
    @JsonIgnore
    public LocalDateTime getModifiedAt() {
        return super.getModifiedAt();
    }
}
