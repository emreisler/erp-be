package github.com.emreisler.erp_be.repository;


import github.com.emreisler.erp_be.entity.Category;
import github.com.emreisler.erp_be.repo_adapter.CategoryRepoAdapter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long>, CategoryRepoAdapter {
}
