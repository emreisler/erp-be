package github.com.emreisler.erp_be.domain.validators;

import github.com.emreisler.erp_be.application.dto.TaskCenterDto;
import github.com.emreisler.erp_be.domain.exception.BadRequestException;
import org.springframework.stereotype.Component;

@Component
public class TaskCenterValidator implements Validator<TaskCenterDto> {

    @Override
    public void validate(TaskCenterDto object) throws BadRequestException {
        if (object == null) {
            throw new BadRequestException("TaskCenter object is null");
        }
        if (object.getName() == null || object.getName().isEmpty()) {
            throw new BadRequestException("TaskCenter name is empty");
        }
        if (object.getNumber() <= 0 || object.getNumber() > 1000) {
            throw new BadRequestException(String.format("TaskCenter number:%d is out of range 0 to 1000", object.getNumber()));
        }
        if (object.getName().length() < 3 || object.getName().length() > 100){
            throw new BadRequestException("TaskCenter name length is invalid");
        }
    }
}
