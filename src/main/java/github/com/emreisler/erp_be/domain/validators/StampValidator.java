package github.com.emreisler.erp_be.domain.validators;

import github.com.emreisler.erp_be.application.dto.StampDto;
import github.com.emreisler.erp_be.domain.exception.BadRequestException;
import github.com.emreisler.erp_be.domain.exception.ErpRuntimeException;
import org.springframework.stereotype.Component;

@Component
public class StampValidator implements Validator<StampDto> {
    @Override
    public void validate(StampDto object) throws ErpRuntimeException {
        if (object == null) {
            throw new BadRequestException("object is null");
        }

        if (object.getUserEmail().isEmpty()){
            throw new BadRequestException("userEmail is empty");
        }
        if (object.getProductionOrderCode().isEmpty()){
            throw new BadRequestException("productionOrderCode is empty");
        }
        if (object.getStepNumber() <= 0){
            throw new BadRequestException("stepNumber is 0 or less");
        }

    }
}
