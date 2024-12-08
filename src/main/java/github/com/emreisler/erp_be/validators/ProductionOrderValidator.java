package github.com.emreisler.erp_be.validators;

import github.com.emreisler.erp_be.dto.CreateProductionOrderRequest;
import github.com.emreisler.erp_be.exception.BadRequestException;
import github.com.emreisler.erp_be.exception.ErpRuntimeException;
import org.springframework.stereotype.Component;

@Component
public class ProductionOrderValidator implements Validator<CreateProductionOrderRequest> {

    @Override
    public void validate(CreateProductionOrderRequest object) throws ErpRuntimeException {
        if (object == null) {
            throw new BadRequestException("object is null");
        }

        if (object.getPartNo().isEmpty()) {
            throw new BadRequestException("partNumber is empty");
        }
        if (object.getQuantity() <= 0) {
            throw new BadRequestException("quantity is 0 or less");
        }

    }
}
