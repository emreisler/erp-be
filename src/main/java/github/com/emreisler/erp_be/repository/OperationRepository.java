package github.com.emreisler.erp_be.repository;

import github.com.emreisler.erp_be.entity.Operation;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface OperationRepository extends CrudRepository<Operation, Long> {
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM operation WHERE part_id = ?1 AND sep_number = ?2", nativeQuery = true)
    void deleteByPartIdAndSepNumber(Long partId, int sepNumber);
}
