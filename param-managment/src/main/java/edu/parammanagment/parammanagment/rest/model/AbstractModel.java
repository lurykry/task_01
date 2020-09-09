package edu.parammanagment.parammanagment.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@ToString
public abstract class AbstractModel extends RepresentationModel<AbstractModel> {

    @JsonProperty("id")
    protected UUID uuid;
    protected LocalDateTime createdAt;
    protected LocalDateTime modifiedAt;
}
