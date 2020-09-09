package edu.parammanagment.parammanagment.domain.core;

import edu.parammanagment.parammanagment.domain.helpers.DataType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Table(name = "parameter")
@Entity
@AttributeOverrides(
        {
                @AttributeOverride(
                        name = "createdAt",
                        column = @Column(name = "param_created_at", nullable = false)
                ),
                @AttributeOverride(
                        name = "uuid",
                        column = @Column(name = "param_id", nullable = false)
                ),
                @AttributeOverride
                        (name = "modifiedAt",
                        column = @Column(name = "param_modified_at", insertable = false, updatable = false))
        }
)
@Getter
@Setter
public class Parameter extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "param_name", nullable = false)
    private String paramName;

    @Column(name = "param_code", nullable = false)
    private String paramCode;

    @Column(name = "param_description", nullable = false)
    private String paramDescription;

    @Column(name = "param_data_type", nullable = true)
    @Enumerated(EnumType.STRING)
    private DataType dataType;

    @OneToOne(mappedBy = "parameter")
    private RelevantData relevantData;

    @OneToMany(mappedBy = "parameter", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @Getter(AccessLevel.PRIVATE)
    private final Set<DataRecord> dataRecords = new HashSet<>();

    public Parameter() {
    }


    public Parameter(String paramName,
                     String paramCode,
                     String paramDescription,
                     DataType dataType) {

        this.paramName = paramName;
        this.paramCode = paramCode;
        this.paramDescription = paramDescription;
        this.dataType = dataType;
    }

    public void addDataRecord(DataRecord dataRecord) {
        dataRecords.add(dataRecord);
        dataRecord.setParameter(this);
    }

    public void removeDataRecord(DataRecord dataRecord) {
        dataRecords.remove(dataRecord);
        dataRecord.setParameter(null);
    }

    public void showDataRecords(){
        dataRecords.forEach(System.out::println);
    }

    @Override
    @Transient
    public LocalDateTime getModifiedAt() {
        return super.getModifiedAt();
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
        return ((Parameter) obj).uuid != null && this.uuid == ((Parameter) obj).uuid;
    }

    @Override
    public String toString() {
        return "Parameter{" +
                "paramName='" + paramName + '\'' +
                ", paramCode='" + paramCode + '\'' +
                ", paramDescription='" + paramDescription + '\'' +
                ", dataType=" + dataType +
                "} " + super.toString();
    }
}