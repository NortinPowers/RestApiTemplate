package by.tms.rest.template.exception;

import static by.tms.rest.template.utils.ResponseUtils.CITY_NOT_FOUND_EXCEPTION_MESSAGE;
import static by.tms.rest.template.utils.ResponseUtils.DATA_INTEGRITY_VIOLATION_EXCEPTION_MESSAGE;
import static by.tms.rest.template.utils.ResponseUtils.HTTP_NOT_READABLE_EXCEPTION_MESSAGE;
import static by.tms.rest.template.utils.ResponseUtils.JPA_OBJECT_RETRIEVAL_FAILURE_EXCEPTION_MESSAGE;
import static by.tms.rest.template.utils.ResponseUtils.METHOD_ARGUMENT_NOT_VALID_EXCEPTION_MESSAGE;
import static by.tms.rest.template.utils.ResponseUtils.NOT_FOUND_EXCEPTION_MESSAGE;
import static by.tms.rest.template.utils.ResponseUtils.getErrorValidationMessages;
import static by.tms.rest.template.utils.ResponseUtils.getExceptionResponse;

import by.tms.rest.template.model.ErrorValidationResponse;
import by.tms.rest.template.model.ExceptionResponse;
import by.tms.rest.template.model.ResponseAble;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    private ResponseEntity<ResponseAble> handleException(NotFoundException exception) {
        ExceptionResponse response = getExceptionResponse(NOT_FOUND_EXCEPTION_MESSAGE, exception);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CityNotFoundException.class)
    private ResponseEntity<ResponseAble> handleException(CityNotFoundException exception) {
        ExceptionResponse response = getExceptionResponse(CITY_NOT_FOUND_EXCEPTION_MESSAGE, exception);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    private ResponseEntity<ResponseAble> handleException(DataIntegrityViolationException exception) {
        ExceptionResponse response = getExceptionResponse(DATA_INTEGRITY_VIOLATION_EXCEPTION_MESSAGE, exception);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(JpaObjectRetrievalFailureException.class)
    private ResponseEntity<ResponseAble> handleException(JpaObjectRetrievalFailureException exception) {
        ExceptionResponse response = getExceptionResponse(JPA_OBJECT_RETRIEVAL_FAILURE_EXCEPTION_MESSAGE, exception);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    private ResponseEntity<ResponseAble> handleException(HttpMessageNotReadableException exception) {
        ExceptionResponse response = getExceptionResponse(HTTP_NOT_READABLE_EXCEPTION_MESSAGE, exception);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ResponseEntity<ResponseAble> handleException(MethodArgumentNotValidException exception) {
        ErrorValidationResponse errorValidationResponse = new ErrorValidationResponse(getErrorValidationMessages(exception), METHOD_ARGUMENT_NOT_VALID_EXCEPTION_MESSAGE);
        return new ResponseEntity<>(errorValidationResponse, HttpStatus.BAD_REQUEST);
    }
}
