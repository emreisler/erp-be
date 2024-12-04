package github.com.emreisler.erp_be.validators;

import github.com.emreisler.erp_be.dto.PartDto;
import github.com.emreisler.erp_be.exception.ObjectNotValidException;
import github.com.emreisler.erp_be.exception.ProjectNotFoundException;
import github.com.emreisler.erp_be.repository.ProjectRepository;
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
    public void validate(PartDto object) throws ObjectNotValidException {
        if (object == null) {
            throw new ObjectNotValidException("PartDto object cannot be null");
        }

        // Validate Part Name
        if (object.getName() == null || object.getName().trim().isEmpty()) {
            throw new ObjectNotValidException("Part name cannot be empty");
        }
        if (!object.getName().matches(VALID_NAME_REGEX)) {
            throw new ObjectNotValidException("Part name contains invalid characters. Only letters, numbers, and '-' are allowed");
        }

        // Validate Part Number
        if (object.getNumber() == null || object.getNumber().trim().isEmpty()) {
            throw new ObjectNotValidException("Part number cannot be empty");
        }
        if (!object.getNumber().matches(VALID_NUMBER_REGEX)) {
            throw new ObjectNotValidException("Part number contains invalid characters. Only letters, numbers, and '-' are allowed");
        }

        // Validate Project Code
        if (object.getProjectCode() == null || object.getProjectCode().trim().isEmpty()) {
            throw new ObjectNotValidException("Project code cannot be empty");
        }
        if (!object.getProjectCode().matches(VALID_NAME_REGEX)) {
            throw new ObjectNotValidException("Project code contains invalid characters. Only letters, numbers, and '-' are allowed");
        }

        //Validate part has a category
        if (object.getCategory() == null) {
            throw new ObjectNotValidException("Category cannot be null");
        }

        // Validate Operation List
        if (object.getOperationList() != null && !object.getOperationList().isEmpty()) {
            throw new ObjectNotValidException("Operation list must be empty");
        }

        // Validate Stocks List
        if (object.getStocksList() != null && !object.getStocksList().isEmpty()) {
            throw new ObjectNotValidException("Stocks list must be empty");
        }

        projectRepository.findByCode(object.getProjectCode())
                .orElseThrow(ProjectNotFoundException::new);
    }
}
