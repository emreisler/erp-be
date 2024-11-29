package github.com.emreisler.erp_be.service;

import github.com.emreisler.erp_be.converters.TaskCenterConverter;
import github.com.emreisler.erp_be.dto.TaskCenterDto;
import github.com.emreisler.erp_be.exception.ExceptionMapper;
import github.com.emreisler.erp_be.exception.TaskCenterNotFoundException;
import github.com.emreisler.erp_be.repository.TaskCenterRepository;
import github.com.emreisler.erp_be.validators.Validator;
import jakarta.validation.executable.ValidateOnExecution;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TaskCenterServiceImpl implements TaskCenterService {


    private final TaskCenterRepository taskCenterRepository;
    private final Validator<TaskCenterDto> taskCenterValidator;

    TaskCenterServiceImpl(TaskCenterRepository taskCenterRepository, Validator<TaskCenterDto> taskCenterValidator) {
        this.taskCenterRepository = taskCenterRepository;
        this.taskCenterValidator = taskCenterValidator;
    }


    @Override
    public List<TaskCenterDto> GetAll() throws Exception {
        var taskCenterEntities = taskCenterRepository.findAll();
        return TaskCenterConverter.toDto(taskCenterEntities);
    }

    @Override
    public TaskCenterDto GetByNumber(int tcNumber) throws Exception {
        var taskCenter = taskCenterRepository.findByNumber(tcNumber).orElseThrow(TaskCenterNotFoundException::new);
        return TaskCenterConverter.toDto(taskCenter);
    }

    @Override
    public TaskCenterDto Create(TaskCenterDto taskCenter) throws Exception {
        taskCenterValidator.validate(taskCenter);

        var taskCenterEntity = TaskCenterConverter.toEntity(taskCenter);
        taskCenterEntity.setUuid(UUID.randomUUID());
        try {
            return TaskCenterConverter.toDto(taskCenterRepository.save(taskCenterEntity));
        } catch (Exception e) {
            throw ExceptionMapper.mapHibernateException(e);
        }
    }

    @Override
    public TaskCenterDto Update(int tcNumber, TaskCenterDto taskCenter) throws Exception {

        taskCenterValidator.validate(taskCenter);

        var updatedTaskCenter = taskCenterRepository.findByNumber(tcNumber).map(tc -> {
            tc.setNumber(taskCenter.getNumber());
            tc.setName(taskCenter.getName());
            tc.setInspection(taskCenter.getInspection());
            return taskCenterRepository.save(tc);
        }).orElseThrow(TaskCenterNotFoundException::new);
        return TaskCenterConverter.toDto(updatedTaskCenter);
    }

    @Override
    public void Delete(int tcNumber) throws Exception {
        if (!taskCenterRepository.existsByNumber(tcNumber)) {
            throw new TaskCenterNotFoundException();
        }
        taskCenterRepository.deleteByNumber(tcNumber);
    }
}
