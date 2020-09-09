package edu.parammanagment.parammanagment.repository;

import edu.parammanagment.parammanagment.domain.core.AbstractEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.UUID;

@NoRepositoryBean
public interface AbstractRepository<E extends AbstractEntity> extends JpaRepository<E, UUID> {

}
