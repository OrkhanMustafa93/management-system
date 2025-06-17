package az.hamburg.managementsystem.service;

import az.hamburg.managementsystem.common.SelectIds;
import az.hamburg.managementsystem.domain.RoleType;
import az.hamburg.managementsystem.domain.User;
import az.hamburg.managementsystem.model.user.request.UserCreateRequest;
import az.hamburg.managementsystem.model.user.request.UserUpdateRequest;
import az.hamburg.managementsystem.model.user.response.*;

import java.util.List;

public interface UserService {

    UserCreateResponse create(UserCreateRequest createRequest);

    UserReadResponse getId(Long id);

    List<UserReadResponse> getByIds(SelectIds selectIds);


    List<UserReadResponse> getAll();

    UserUpdateResponse update(Long id , UserUpdateRequest updateRequest);

    void delete (Long id);


    UserRoleUpdateResponse roleUpdate(Long id, Long changerId, RoleType roleType);

    UserStatusUpdateResponse statusUpdate(Long id, Long changerId, boolean status);

    List<UserReadResponse> getAllUserByOrganizationId(Long organizationId);
}
