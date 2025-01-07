package github.com.emreisler.erp_be.persistence.repository;


import github.com.emreisler.erp_be.persistence.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
