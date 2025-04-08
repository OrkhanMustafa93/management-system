package az.hamburg.managementsystem.exception.handler;

public class ContactLinkNotFoundException extends RuntimeException {

    public ContactLinkNotFoundException(String message ,String code) {
        super(message);
    }
}
