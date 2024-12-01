package github.com.emreisler.erp_be.validators;

import github.com.emreisler.erp_be.dto.StockDto;
import github.com.emreisler.erp_be.exception.ObjectNotValidException;
import org.springframework.stereotype.Component;

@Component
public class StockValidator implements Validator<StockDto> {


    @Override
    public void validate(StockDto object) throws ObjectNotValidException {
        if (object == null) {
            throw new ObjectNotValidException("object is null");
        }
        if (object.getName() == null || object.getName().isEmpty()) {
            throw new ObjectNotValidException("name is empty");
        }
        if (object.getQuantity() <= 0) {
            throw new ObjectNotValidException("quantity can nat be zero or less");
        }
        if (object.getUnit() == null) {
            throw new ObjectNotValidException("unit is null");
        }

    }
}
