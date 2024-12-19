package github.com.emreisler.erp_be.validators;

import github.com.emreisler.erp_be.dto.AssemblyDto;
import github.com.emreisler.erp_be.exception.BadRequestException;
import github.com.emreisler.erp_be.exception.ErpRuntimeException;
import org.springframework.stereotype.Component;

@Component
public class AssemblyValidator implements Validator<AssemblyDto> {

    private static final String VALID_NUMBER_REGEX = "^[a-zA-Z0-9-]+$";
    private static final String VALID_NAME_REGEX = "^[a-zA-Z0-9- ]+$";

    @Override
    public void validate(AssemblyDto object) throws ErpRuntimeException {
        if (object == null) {
            throw new BadRequestException("assembly is null");
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


    }
}
