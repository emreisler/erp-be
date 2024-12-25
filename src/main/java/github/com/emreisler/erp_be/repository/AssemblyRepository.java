package github.com.emreisler.erp_be.repository;

import github.com.emreisler.erp_be.entity.Assembly;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AssemblyRepository extends JpaRepository<Assembly, Long> {
    List<Assembly> findByProjectCode(String projectCode);

    Optional<Assembly> findByNumber(String number);
}
