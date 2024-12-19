package github.com.emreisler.erp_be.repository;

import github.com.emreisler.erp_be.entity.Assembly;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssemblyRepository extends JpaRepository<Assembly, Long> {
    List<Assembly> findByProjectCode(String projectCode);

}
