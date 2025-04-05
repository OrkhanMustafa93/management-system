package az.hamburg.managementsystem.exception.handler;

public class UserUnAuthorizedException extends RuntimeException {
    public UserUnAuthorizedException(String message, String code) {
        super(message);
    }
}
