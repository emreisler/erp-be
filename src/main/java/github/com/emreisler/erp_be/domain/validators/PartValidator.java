package github.com.emreisler.erp_be.domain.validators;

import github.com.emreisler.erp_be.application.dto.PartDto;
import github.com.emreisler.erp_be.domain.exception.BadRequestException;
import github.com.emreisler.erp_be.domain.exception.NotFoundException;
import github.com.emreisler.erp_be.persistence.repository.ProjectRepository;
import org.springframework.stereotype.Component;

@Component
public class PartValidator implements Validator<PartDto> {

    private static final String VALID_NUMBER_REGEX = "^[a-zA-Z0-9-]+$";
    private static final String VALID_NAME_REGEX = "^[a-zA-Z0-9- ]+$";

    private final ProjectRepository projectRepository;

    public PartValidator(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public void validate(PartDto object) throws BadRequestException {
        if (object == null) {
            throw new BadRequestException("PartDto object cannot be null");
        }

        // Validate Part Name
        if (object.getName() == null || object.getName().trim().isEmpty()) {
            throw new BadRequestException("Part name cannot be empty");
        }
        if (!object.getName().matches(VALID_NAME_REGEX)) {
            throw new BadRequestException("Part name contains invalid characters. Only letters, numbers, and '-' are allowed");
        }

        // Validate Part Number
        if (object.getNumber() == null || object.getNumber().trim().isEmpty()) {
            throw new BadRequestException("Part number cannot be empty");
        }
        if (!object.getNumber().matches(VALID_NUMBER_REGEX)) {
            throw new BadRequestException("Part number contains invalid characters. Only letters, numbers, and '-' are allowed");
        }

        // Validate Project Code
        if (object.getProjectCode() == null || object.getProjectCode().trim().isEmpty()) {
            throw new BadRequestException("Project code cannot be empty");
        }
        if (!object.getProjectCode().matches(VALID_NAME_REGEX)) {
            throw new BadRequestException("Project code contains invalid characters. Only letters, numbers, and '-' are allowed");
        }

        //Validate part has a category
        if (object.getCategory() == null) {
            throw new BadRequestException("Category cannot be null");
        }

        // Validate Operation List
        if (object.getOperationList() != null && !object.getOperationList().isEmpty()) {
            throw new BadRequestException("Operation list must be empty");
        }

        // Validate Stocks List
        if (object.getStocksList() != null && !object.getStocksList().isEmpty()) {
            throw new BadRequestException("Stocks list must be empty");
        }

        projectRepository.findByCode(object.getProjectCode())
                .orElseThrow(()-> new NotFoundException("Project code not found"));
    }
}
