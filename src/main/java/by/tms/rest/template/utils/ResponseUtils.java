package by.tms.rest.template.utils;

import by.tms.rest.template.model.ExceptionResponse;
import by.tms.rest.template.model.MessageResponse;
import java.util.List;
import java.util.stream.Collectors;
import lombok.experimental.UtilityClass;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.web.bind.MethodArgumentNotValidException;

@UtilityClass
public class ResponseUtils {

    public static final String CREATION_MESSAGE = "The %s have been successful created";
    public static final String UPDATE_MESSAGE = "The %s have been successful updated";
    public static final String DELETION_MESSAGE = "The %s have been successful deleted";
    public static final String NOT_FOUND_EXCEPTION_MESSAGE = "Specify the entered data";
    public static final String CITY_NOT_FOUND_EXCEPTION_MESSAGE = "The entered city is not listed in the database";
    public static final String DATA_INTEGRITY_VIOLATION_EXCEPTION_MESSAGE = "The input data does not correspond to the required";
    public static final String JPA_OBJECT_RETRIEVAL_FAILURE_EXCEPTION_MESSAGE = "The data entered violates the established requirements";
    public static final String HTTP_NOT_READABLE_EXCEPTION_MESSAGE = "The entered data is incorrect and leads to an error";
    public static final String METHOD_ARGUMENT_NOT_VALID_EXCEPTION_MESSAGE = "The transmitted data did not pass verification";

    public static <T> MessageResponse getSuccessResponse(String message, T t) {
        return new MessageResponse(String.format(message, getClassName(t)), t);
    }

    public static ExceptionResponse getExceptionResponse(String message, Exception exception) {
        return new ExceptionResponse(message, exception.getClass().getSimpleName());
    }

    private static <T> String getClassName(T t) {
        String className = t.getClass().getSimpleName().toLowerCase();
        return className.substring(0, className.length() - 3);
    }

    public static List<String> getErrorValidationMessages(MethodArgumentNotValidException exception) {
        return exception.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
    }
}
