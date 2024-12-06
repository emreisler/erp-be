package github.com.emreisler.erp_be.validators;

import github.com.emreisler.erp_be.exception.ErpRuntimeException;

public interface Validator<T> {
    void validate(T object) throws ErpRuntimeException;
}
