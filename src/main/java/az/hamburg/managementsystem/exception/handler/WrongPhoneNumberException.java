package az.hamburg.managementsystem.exception.handler;

public class WrongPhoneNumberException extends RuntimeException {
    public WrongPhoneNumberException(String message, String code) {
        super(message);
    }
}
