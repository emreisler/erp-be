package github.com.emreisler.erp_be.validators;

import github.com.emreisler.erp_be.dto.OperationDto;
import github.com.emreisler.erp_be.exception.BadRequestException;
import github.com.emreisler.erp_be.exception.ErpRuntimeException;

public class OperationValidator implements Validator<OperationDto> {

    @Override
    public void validate(OperationDto object) throws ErpRuntimeException {

        if (object == null) {
            throw new BadRequestException("Operation object is null");
        }

        if (object.getSepNumber() <= 0) {
            throw new BadRequestException(String.format("Part operation number must be greater than zero", object.getSepNumber()));
        }
    }
}
