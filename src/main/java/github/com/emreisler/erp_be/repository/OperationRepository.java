package github.com.emreisler.erp_be.repository;

import github.com.emreisler.erp_be.entity.Operation;
import org.springframework.data.repository.CrudRepository;

public interface OperationRepository extends CrudRepository<Operation, Long> {
}
