package github.com.emreisler.erp_be.domain.service.taskCenter;

import github.com.emreisler.erp_be.application.dto.TaskCenterDto;

import java.util.List;


public interface TaskCenterService {

    List<TaskCenterDto> GetAll() throws Exception;

    TaskCenterDto GetByNumber(int tcNumber) throws Exception;

    TaskCenterDto Create(TaskCenterDto taskCenter) throws Exception;

    TaskCenterDto Update(int tcNumber, TaskCenterDto taskCenter) throws Exception;

    void Delete(int tcNumber) throws Exception;
}
