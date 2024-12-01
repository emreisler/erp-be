package github.com.emreisler.erp_be.validators;

import github.com.emreisler.erp_be.dto.ProjectDto;
import github.com.emreisler.erp_be.exception.ObjectNotValidException;
import org.springframework.stereotype.Component;

@Component
public class ProjectValidator implements Validator<ProjectDto> {



    @Override
    public void validate(ProjectDto object) throws ObjectNotValidException {
        if (object.getName() == null || object.getName().isEmpty()) {
            throw new ObjectNotValidException("Name is required");
        }
        if (object.getCode() == null || object.getCode().isEmpty()) {
            throw new ObjectNotValidException("Code is required");
        }



    }
}
