package edu.parammanagment.parammanagment.repository;

import edu.parammanagment.parammanagment.domain.core.Parameter;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ParameterRepository extends AbstractRepository<Parameter> {


    @Query(value="SELECT p FROM Parameter p left join fetch p.dataRecords WHERE p.uuid = :uuid")
    Optional<Parameter> findByIdWithRecords(@Param("uuid") UUID uuid);
}
