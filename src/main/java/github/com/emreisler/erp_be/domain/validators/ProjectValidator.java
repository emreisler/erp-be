package github.com.emreisler.erp_be.domain.validators;

import github.com.emreisler.erp_be.application.dto.ProjectDto;
import github.com.emreisler.erp_be.domain.exception.BadRequestException;
import org.springframework.stereotype.Component;

@Component
public class ProjectValidator implements Validator<ProjectDto> {


    @Override
    public void validate(ProjectDto object) throws BadRequestException {
        if (object.getName() == null || object.getName().isEmpty()) {
            throw new BadRequestException("Name is required");
        }
        if (object.getCode() == null || object.getCode().isEmpty()) {
            throw new BadRequestException("Code is required");
        }
    }
}
