package az.hamburg.managementsystem.mappers;

import az.hamburg.managementsystem.domain.User;
import az.hamburg.managementsystem.model.user.request.UserCreateRequest;
import az.hamburg.managementsystem.model.user.request.UserUpdateRequest;
import az.hamburg.managementsystem.model.user.response.*;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)

public interface UserMapper {

    User createRequestToEntity(UserCreateRequest createRequest);

    UserCreateResponse entityToCreateResponse(User user);

    UserReadResponse entityToReadResponse(User user);

    User updateRequestToEntity(UserUpdateRequest updateRequest);

    UserUpdateResponse entityToUpdateResponse(User user);

    UserStatusUpdateResponse entityToUserStatusUpdateResponse(User user);

    UserRoleUpdateResponse entityToUserRoleUpdateResponse(User user);

}
