package github.com.emreisler.erp_be.validators;

import github.com.emreisler.erp_be.dto.StockDto;
import github.com.emreisler.erp_be.exception.BadRequestException;
import org.springframework.stereotype.Component;

@Component
public class StockValidator implements Validator<StockDto> {


    @Override
    public void validate(StockDto object) throws BadRequestException {
        if (object == null) {
            throw new BadRequestException("object is null");
        }
        if (object.getName() == null || object.getName().isEmpty()) {
            throw new BadRequestException("name is empty");
        }
        if (object.getQuantity() <= 0) {
            throw new BadRequestException("quantity can nat be zero or less");
        }
        if (object.getUnit() == null) {
            throw new BadRequestException("unit is null");
        }

    }
}
