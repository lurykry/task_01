package edu.parammanagment.parammanagment.domain.core;

import edu.parammanagment.parammanagment.domain.helpers.ParameterTypeAndValue;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A single record, containing relevant and up-to-date info about {@link Parameter}.
 * @author Kirill Mansurov
 * @version 1.0
 */
@Table(name = "relevant_data")
@Entity
@AttributeOverrides(
        {
                @AttributeOverride(
                        name = "createdAt",
                        column = @Column(name = "data_created_at", nullable = false)
                ),
                @AttributeOverride(
                        name = "modifiedAt",
                        column = @Column(name = "data_modified_at", nullable = false)
                ),
                @AttributeOverride(
                        name = "uuid",
                        column = @Column(name = "data_id", nullable = false)
                )
        }
)
@Getter
@Setter
public class RelevantData extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "data_record_date")
    private LocalDateTime recordDate;

    @Type(type = "jsonb")
    @Column(name = "data_param_val", columnDefinition = "jsonb")
    private ParameterTypeAndValue parameterTypeAndValue;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "data_param_id")
    private Parameter parameter;

    @OneToMany(mappedBy = "relevantData", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true)
    private final Set<DataRecord> dataRecords = new HashSet<>();

    public RelevantData() {
    }

    public RelevantData(LocalDateTime recordDate,
                        ParameterTypeAndValue parameterTypeAndValue,
                        Parameter parameter) {
        this.recordDate = recordDate;
        this.parameterTypeAndValue = parameterTypeAndValue;
        this.parameter = parameter;
    }

    public void addDataRecord(DataRecord dataRecord) {
        dataRecords.add(dataRecord);
        dataRecord.setRelevantData(this);
    }

    public void removeDataRecord(DataRecord dataRecord) {
        dataRecords.remove(dataRecord);
        dataRecord.setRelevantData(null);
    }

    public Set<DataRecord> getDataRecords(){
        return Collections.unmodifiableSet(dataRecords);
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
        return ((RelevantData) obj).uuid != null && this.uuid == ((RelevantData) obj).uuid;
    }

    @Override
    public String toString() {
        return "CurrentData{" +
                "recordDate=" + recordDate +
                ", parameterTypeAndValue=" + parameterTypeAndValue +
                ", parameter=" + parameter +
                ", dataRecords=" + dataRecords +
                "} " + super.toString();
    }
}