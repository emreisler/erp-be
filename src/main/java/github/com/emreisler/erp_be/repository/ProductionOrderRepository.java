package github.com.emreisler.erp_be.repository;

import github.com.emreisler.erp_be.entity.ProductionOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductionOrderRepository extends JpaRepository<ProductionOrder, Long> {
    List<ProductionOrder> findByPartNumber(String partNumber);

    List<ProductionOrder> findByCurrentTaskCenter(int currentTaskCenter);

    Optional<ProductionOrder> findByCode(String code);
}
