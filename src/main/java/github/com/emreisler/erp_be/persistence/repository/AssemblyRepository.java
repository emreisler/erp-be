package github.com.emreisler.erp_be.persistence.repository;

import github.com.emreisler.erp_be.persistence.entity.Assembly;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AssemblyRepository extends JpaRepository<Assembly, Long> {
    List<Assembly> findByProjectCode(String projectCode);

    List<Assembly> findAllByOrderByUpdatedAtDesc();

    Optional<Assembly> findByNumber(String number);
}
