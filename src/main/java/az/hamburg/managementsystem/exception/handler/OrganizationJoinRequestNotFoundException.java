package az.hamburg.managementsystem.exception.handler;

public class OrganizationJoinRequestNotFoundException extends RuntimeException {

    public OrganizationJoinRequestNotFoundException(String message,String code) {
        super(message);
    }
}
