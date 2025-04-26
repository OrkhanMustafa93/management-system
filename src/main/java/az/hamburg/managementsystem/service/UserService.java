package az.hamburg.managementsystem.service;

import az.hamburg.managementsystem.domain.RoleType;
import az.hamburg.managementsystem.model.user.request.UserCreateRequest;
import az.hamburg.managementsystem.model.user.request.UserUpdateRequest;
import az.hamburg.managementsystem.model.user.response.*;

import java.util.List;

public interface UserService {

    UserCreateResponse create(UserCreateRequest createRequest);

    UserReadResponse getId(Long id);

    List<UserReadResponse> getAll();

    UserUpdateResponse update(Long id , UserUpdateRequest updateRequest);

    void delete (Long id);

    //!!!!!!!!!!!!
//    UserUpdateResponse statusUpdate(Long id, Long userId);

    UserRoleUpdateResponse roleUpdate(Long id, Long changerId, RoleType roleType);

    UserStatusUpdateResponse statusUpdate(Long id, Long changerId, boolean status);


////    UserUpdateResponse statusUpdate(Long id, UserUpdateRequest updateRequest, Long userId);
//
//    UserUpdateResponse statusUpdate(Long id, Long userId);
//
//
//
//    UserUpdateResponse roleUpdate(Long id, UserUpdateRequest userUpdateRequest, Long userId);
}
