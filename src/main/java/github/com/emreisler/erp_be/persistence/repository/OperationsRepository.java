package github.com.emreisler.erp_be.persistence.repository;

import github.com.emreisler.erp_be.persistence.entity.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OperationsRepository extends JpaRepository<Operation, Long> {

    List<Operation> findByPartId(Long partId);
}
