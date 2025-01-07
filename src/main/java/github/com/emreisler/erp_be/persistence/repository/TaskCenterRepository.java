package github.com.emreisler.erp_be.persistence.repository;

import github.com.emreisler.erp_be.persistence.entity.TaskCenter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaskCenterRepository extends JpaRepository<TaskCenter, Long> {
    Optional<TaskCenter> findByNumber(int number) throws Exception;
    void deleteByNumber(int number) throws Exception;
    boolean existsByNumber(int number) throws Exception;
    boolean existsByName(String name) throws Exception;
}
