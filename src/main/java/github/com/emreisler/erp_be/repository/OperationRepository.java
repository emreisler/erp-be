package github.com.emreisler.erp_be.repository;

import github.com.emreisler.erp_be.entity.Operation;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

public interface OperationRepository extends CrudRepository<Operation, Long> {
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM operation WHERE part_id = ?1 AND step_number = ?2", nativeQuery = true)
    void deleteByPartIdAndStepNumber(Long partId, int stepNumber);

    Optional<Operation> findByOperationId(UUID operationId);
}
