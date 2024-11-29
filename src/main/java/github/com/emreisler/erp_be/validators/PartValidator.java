package github.com.emreisler.erp_be.validators;

import github.com.emreisler.erp_be.dto.TaskCenterDto;
import github.com.emreisler.erp_be.exception.ObjectNotValidException;
import org.springframework.stereotype.Component;

@Component
public class PartValidator implements Validator<TaskCenterDto> {
    @Override
    public void validate(TaskCenterDto object) throws ObjectNotValidException {

    }
}
