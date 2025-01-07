package github.com.emreisler.erp_be.persistence.repository;

import github.com.emreisler.erp_be.persistence.entity.ProductionOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductionOrderRepository extends JpaRepository<ProductionOrder, Long> {
    List<ProductionOrder> findByPartNumber(String partNumber);

    List<ProductionOrder> findAllByOrderByEndDateAsc();

    List<ProductionOrder> findByCurrentTaskCenter(int currentTaskCenter);

    Optional<ProductionOrder> findByCode(String code);
}
