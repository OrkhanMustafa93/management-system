package az.hamburg.managementsystem.mappers;

import az.hamburg.managementsystem.domain.User;
import az.hamburg.managementsystem.model.request.UserCreateRequest;
import az.hamburg.managementsystem.model.request.UserUpdateRequest;
import az.hamburg.managementsystem.model.response.UserCreateResponse;
import az.hamburg.managementsystem.model.response.UserReadResponse;
import az.hamburg.managementsystem.model.response.UserUpdateResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)

public interface UserMapper {

    User createRequestToEntity(UserCreateRequest createRequest);

    UserCreateResponse entityToCreateResponse(User user);

    UserReadResponse entityToReadResponse(User user);

    User updateRequestToEntity(UserUpdateRequest updateRequest);

    UserUpdateResponse entityToUpdateResponse(User user);

}
