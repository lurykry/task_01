package edu.parammanagment.parammanagment.repository;

import edu.parammanagment.parammanagment.domain.core.RelevantData;
import edu.parammanagment.parammanagment.domain.helpers.ParameterTypeAndValue;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Kirill Mansurov
 * @version 1.0
 */
@Repository
public interface RelevantDataRepository extends AbstractRepository<RelevantData> {

    @Query(value="SELECT c FROM RelevantData c left join fetch c.dataRecords WHERE c.uuid = :uuid")
    Optional<RelevantData> findByIdWithRecords(@Param("uuid") UUID uuid);

    @Modifying
    @Query(value = "UPDATE RelevantData c SET c.recordDate = :recordDate ," +
            " c.parameterTypeAndValue = :parameterTypeAndValue where c.uuid = :uuid")
    void updateRelevantData(@Param("uuid") UUID uuid, @Param("recordDate") LocalDateTime recordDate,
                            @Param("parameterTypeAndValue") ParameterTypeAndValue parameterTypeAndValue);
}
