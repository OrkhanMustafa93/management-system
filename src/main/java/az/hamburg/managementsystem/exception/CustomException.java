package az.hamburg.managementsystem.exception;

import az.hamburg.managementsystem.exception.error.ErrorResponse;
import az.hamburg.managementsystem.exception.handler.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.*;

@Slf4j
@RestControllerAdvice
public class CustomException {

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ProblemDetail handlerUserNotFound(UserNotFoundException e){
        return ProblemDetail.forStatusAndDetail(NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(OrganizationNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ProblemDetail handlerOrganizationNotFound(OrganizationNotFoundException e) {
        return ProblemDetail.forStatusAndDetail(NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(UserUnAuthorizedException.class)
    @ResponseStatus(UNAUTHORIZED)
    public ProblemDetail handlerUserUnAuthorized(UserUnAuthorizedException e) {
        return ProblemDetail.forStatusAndDetail(UNAUTHORIZED, e.getMessage());
    }

    @ExceptionHandler(ContactNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ProblemDetail handlerContactNotFound(ContactNotFoundException e) {
        return ProblemDetail.forStatusAndDetail(NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(ContactLinkNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ProblemDetail handlerContactLinkNotFound(ContactLinkNotFoundException e) {
        return ProblemDetail.forStatusAndDetail(NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(BAD_REQUEST)
    public ErrorResponse handlerMethodArgumentNotValid(MethodArgumentNotValidException exception) {
        String fieldName = exception.getBindingResult().getFieldError().getField();
        String message = exception.getBindingResult().getFieldError().getDefaultMessage();

        return ErrorResponse.builder()
                .message(fieldName + message)
                .code(BAD_REQUEST.name())
                .build();

    }

}