package github.com.emreisler.erp_be.repository;

import github.com.emreisler.erp_be.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    Optional<Project> findByCode(String code);
    boolean existsByCode(String code);
    void deleteByCode(String code);
}
