package github.com.emreisler.erp_be.persistence.repository;

import github.com.emreisler.erp_be.persistence.entity.Part;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PartRepository extends JpaRepository<Part, Long> {
    List<Part> findByProjectCode(String projectCode);

    List<Part> findAllByOrderByUpdatedAtDesc();
    Optional<Part> findByNumber(String number);

    Optional<Part> findByName(String name);

    void deleteByNumber(String number);
}
