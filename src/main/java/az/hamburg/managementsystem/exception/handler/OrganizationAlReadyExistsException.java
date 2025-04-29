package az.hamburg.managementsystem.exception.handler;

public class OrganizationAlReadyExistsException extends  RuntimeException{

    public OrganizationAlReadyExistsException(String message,String code ) {
        super(message);
    }
}
