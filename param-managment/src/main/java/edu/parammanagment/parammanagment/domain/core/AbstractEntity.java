package edu.parammanagment.parammanagment.domain.core;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import java.util.UUID;



@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@TypeDefs({
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})
@Getter
@Setter
public abstract class AbstractEntity{

    @Id
    @GeneratedValue
    protected UUID uuid;

    @CreatedDate
    protected LocalDateTime createdAt;

    @LastModifiedDate
    protected LocalDateTime modifiedAt;

    @Override
    public String toString() {
        return "AbstractEntity{" +
                "uuid=" + uuid +
                ", createdAt=" + createdAt +
                ", modifiedAt=" + modifiedAt +
                "} " + super.toString();
    }
}