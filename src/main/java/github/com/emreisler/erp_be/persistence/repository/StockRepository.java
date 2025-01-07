package github.com.emreisler.erp_be.persistence.repository;

import github.com.emreisler.erp_be.persistence.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, Long> {
    Optional<Stock> findByName(String name);
    Optional<Stock> findByCode(String code);
    void deleteByCode(String code);
}
