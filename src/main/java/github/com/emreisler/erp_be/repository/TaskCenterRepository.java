package github.com.emreisler.erp_be.repository;

import github.com.emreisler.erp_be.entity.TaskCenterDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaskCenterRepository extends JpaRepository<TaskCenterDto, Long> {
    Optional<TaskCenterDto> findByNumber(int number) throws Exception;
    void deleteByNumber(int number) throws Exception;
    boolean existsByNumber(int number) throws Exception;
    boolean existsByName(String name) throws Exception;
}
