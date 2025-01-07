package github.com.emreisler.erp_be.domain.validators;

import github.com.emreisler.erp_be.application.dto.CreatePartProductionOrderRequest;
import github.com.emreisler.erp_be.domain.exception.BadRequestException;
import github.com.emreisler.erp_be.domain.exception.ErpRuntimeException;
import org.springframework.stereotype.Component;

@Component
public class ProductionOrderValidator implements Validator<CreatePartProductionOrderRequest> {

    @Override
    public void validate(CreatePartProductionOrderRequest object) throws ErpRuntimeException {
        if (object == null) {
            throw new BadRequestException("object is null");
        }

        if (object.getPartNo().isEmpty()) {
            throw new BadRequestException("partNumber is empty");
        }
        if (object.getQuantity() <= 0) {
            throw new BadRequestException("quantity is 0 or less");
        }
        if (object.getEndDate() == null) {
            throw new BadRequestException("endDate is null");
        }

    }
}
