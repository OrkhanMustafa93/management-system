package az.hamburg.managementsystem.exception.handler;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String message, String code) {
        super(message);
    }
}
