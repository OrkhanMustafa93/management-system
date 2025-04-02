package az.hamburg.managementsystem.service;

import az.hamburg.managementsystem.model.request.UserCreateRequest;
import az.hamburg.managementsystem.model.request.UserUpdateRequest;
import az.hamburg.managementsystem.model.response.UserCreateResponse;
import az.hamburg.managementsystem.model.response.UserReadResponse;
import az.hamburg.managementsystem.model.response.UserUpdateResponse;

import java.util.List;

public interface UserService {

    UserCreateResponse create(UserCreateRequest createRequest);

    UserReadResponse getId(Long id);

    List<UserReadResponse> getAll();

    UserUpdateResponse update(Long id , UserUpdateRequest updateRequest);

    void delete (Long id);

}
