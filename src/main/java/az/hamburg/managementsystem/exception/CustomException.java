package az.hamburg.managementsystem.exception;

import az.hamburg.managementsystem.exception.handler.OrganizationNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@RestControllerAdvice
public class CustomException {

    @ExceptionHandler(OrganizationNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ProblemDetail handlerOrganizationNotFound(OrganizationNotFoundException e) {
        return ProblemDetail.forStatusAndDetail(NOT_FOUND, e.getMessage());
    }
}