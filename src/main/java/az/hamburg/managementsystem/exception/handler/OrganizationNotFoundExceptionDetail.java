package az.hamburg.managementsystem.exception.handler;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
public class OrganizationNotFoundExceptionDetail extends RuntimeException{

    private final List<Long> notFoundIds;

    public OrganizationNotFoundExceptionDetail(List<Long> notFoundIds) {
        super("Organization not found: " + notFoundIds);
        this.notFoundIds = notFoundIds;
    }
}
