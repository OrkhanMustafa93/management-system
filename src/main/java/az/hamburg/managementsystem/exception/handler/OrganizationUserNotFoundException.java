package az.hamburg.managementsystem.exception.handler;

public class OrganizationUserNotFoundException extends RuntimeException{

    public OrganizationUserNotFoundException(String message, String code) {
        super(message);

    }
}
