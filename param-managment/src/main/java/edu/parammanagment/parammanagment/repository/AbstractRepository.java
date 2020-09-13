package edu.parammanagment.parammanagment.repository;

import edu.parammanagment.parammanagment.domain.core.AbstractEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.UUID;

/**
 * Repository, providing standard (CRUD and paging/sorting) methods for AbstractEntity extenders.
 * @param <E> is an entity, extending {@link AbstractEntity}.
 * @author Kirill Mansurov
 * @version 1.0
 */
@NoRepositoryBean
public interface AbstractRepository<E extends AbstractEntity> extends JpaRepository<E, UUID> {

}
