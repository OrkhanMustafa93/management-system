package az.hamburg.managementsystem.exception.handler;

import az.hamburg.managementsystem.common.SelectIds;
import az.hamburg.managementsystem.exception.error.ErrorMessage;
import lombok.Getter;
import java.util.List;
import java.util.Set;

@Getter
public class UsersWithTheseIdsNotFoundException extends RuntimeException {
    private SelectIds selectIds;

    public UsersWithTheseIdsNotFoundException(SelectIds selectIds) {
        super("Users with given IDs not found");
        this.selectIds = selectIds;
    }

}

