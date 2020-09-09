package edu.parammanagment.parammanagment.domain.core;

import edu.parammanagment.parammanagment.domain.helpers.ParameterTypeAndValue;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Table(name = "data_history")
@Entity
@AttributeOverrides(
        {
                @AttributeOverride(
                        name = "createdAt",
                        column = @Column(name = "data_history_created_at", nullable = false)
                ),
                @AttributeOverride(
                        name = "modifiedAt",
                        column = @Column(name = "data_history_modified_at", nullable = false)
                ),
                @AttributeOverride(
                        name = "uuid",
                        column = @Column(name = "data_history_id", nullable = false)
                )
        }
)
@Getter
@Setter
public class DataRecord extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "data_history_record_date")
    private LocalDateTime recordDate;

    @Type(type = "jsonb")
    @Column(name = "data_history_param_val", columnDefinition = "jsonb")
    private ParameterTypeAndValue parameterTypeAndValue;

    @ManyToOne
    @JoinColumn(name = "data_history_param_id", nullable=false)
    private Parameter parameter;

    @ManyToOne
    @JoinColumn(name = "data_history_relevant_data_id", nullable=false)
    private RelevantData relevantData;

    public DataRecord() {
    }

    public DataRecord(LocalDateTime recordDate, ParameterTypeAndValue parameterTypeAndValue, Parameter parameter) {
        this.recordDate = recordDate;
        this.parameterTypeAndValue = parameterTypeAndValue;
        this.parameter = parameter;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(uuid);
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null) return false;
        if(this.getClass() != obj.getClass()) return false;
        return ((DataRecord) obj).uuid != null && this.uuid == ((DataRecord) obj).uuid;
    }

    @Override
    public String toString() {
        return "DataRecord{" +
                "recordDate=" + recordDate +
                ", parameterTypeAndValue=" + parameterTypeAndValue +
                ", parameter=" + parameter.getUuid() +
                ", currentData=" + relevantData.getUuid() +
                "} " + super.toString();
    }
}
