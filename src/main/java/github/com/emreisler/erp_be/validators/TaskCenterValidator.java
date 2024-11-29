package github.com.emreisler.erp_be.validators;

import github.com.emreisler.erp_be.dto.TaskCenterDto;
import github.com.emreisler.erp_be.exception.ObjectNotValidException;
import org.springframework.stereotype.Component;

@Component
public class TaskCenterValidator implements Validator<TaskCenterDto> {

    @Override
    public void validate(TaskCenterDto object) throws ObjectNotValidException {
        System.out.println("TaskCenter validator");
        if (object == null) {
            throw new ObjectNotValidException("TaskCenter object is null");
        }
        if (object.getName() == null || object.getName().isEmpty()) {
            throw new ObjectNotValidException("TaskCenter name is empty");
        }
        if (object.getNumber() <= 0 || object.getNumber() > 1000) {
            throw new ObjectNotValidException(String.format("TaskCenter number:%d is out of range 0 to 1000", object.getNumber()));
        }
    }
}
