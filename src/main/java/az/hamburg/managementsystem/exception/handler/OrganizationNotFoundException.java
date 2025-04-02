package az.hamburg.managementsystem.exception.handler;

public class OrganizationNotFoundException extends  RuntimeException{

    public OrganizationNotFoundException(String message , String code) {
        super(message);
    }
}
