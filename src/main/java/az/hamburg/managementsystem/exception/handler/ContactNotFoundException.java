package az.hamburg.managementsystem.exception.handler;

public class ContactNotFoundException extends RuntimeException{

    public ContactNotFoundException(String message , String code) {
        super(message);

    }
}
