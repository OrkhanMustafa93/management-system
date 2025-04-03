package az.hamburg.managementsystem.exception;

import az.hamburg.managementsystem.exception.handler.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@RestControllerAdvice
public class CustomException {

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ProblemDetail handlerUserNotFound(UserNotFoundException e) {
        return ProblemDetail.forStatusAndDetail(NOT_FOUND, e.getMessage());
    }
}
