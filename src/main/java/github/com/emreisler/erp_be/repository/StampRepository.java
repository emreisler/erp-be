package github.com.emreisler.erp_be.repository;

import github.com.emreisler.erp_be.entity.Stamp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StampRepository extends JpaRepository<Stamp, Long> {
    boolean existsByProductionOrderCodeAndStepNumber(String code, int stepNumber);

    List<Stamp> findByProductionOrderCode(String code);

    void deleteByProductionOrderCodeAndStepNumber(String code, int stepNumber);
}
