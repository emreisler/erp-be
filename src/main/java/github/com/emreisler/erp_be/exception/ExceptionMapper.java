package github.com.emreisler.erp_be.exception;

import github.com.emreisler.erp_be.error.ErrorCodes;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;

public class ExceptionMapper {
    public static ErpRuntimeException mapHibernateException(Throwable throwable) {
        if (throwable instanceof ConstraintViolationException) {
            return new ErpRuntimeException(HttpStatus.BAD_REQUEST, ErrorCodes.FIELD_VIOLATION, throwable.getMessage());
        }
        if (throwable instanceof DataIntegrityViolationException) {
            return new ErpRuntimeException(HttpStatus.BAD_REQUEST, ErrorCodes.FIELD_VIOLATION, throwable.getMessage());
        }
        if (throwable instanceof org.hibernate.exception.SQLGrammarException) {
            return new ErpRuntimeException("SQL grammar error: " + throwable.getMessage());
        }
        return new ErpRuntimeException("General database error: " + throwable.getMessage());
    }
}
