package az.hamburg.managementsystem.exception.handler;

public class AddressNotFoundException extends RuntimeException {
    public AddressNotFoundException(String message, String code) {
        super(message);
    }
}
