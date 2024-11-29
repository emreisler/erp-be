package github.com.emreisler.erp_be.validators;

import github.com.emreisler.erp_be.exception.ObjectNotValidException;

public interface Validator<T> {
    void validate(T object) throws ObjectNotValidException;
}
