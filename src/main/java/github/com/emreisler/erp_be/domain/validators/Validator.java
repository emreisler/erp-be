package github.com.emreisler.erp_be.domain.validators;

import github.com.emreisler.erp_be.domain.exception.ErpRuntimeException;

public interface Validator<T> {
    void validate(T object) throws ErpRuntimeException;
}
