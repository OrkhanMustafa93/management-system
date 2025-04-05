package az.hamburg.managementsystem.service.impl;
import az.hamburg.managementsystem.domain.RoleType;
import az.hamburg.managementsystem.domain.User;
import az.hamburg.managementsystem.exception.error.ErrorMessage;
import az.hamburg.managementsystem.exception.handler.UserNotFoundException;
import az.hamburg.managementsystem.mappers.UserMapper;
import az.hamburg.managementsystem.model.user.request.UserCreateRequest;
import az.hamburg.managementsystem.model.user.request.UserUpdateRequest;
import az.hamburg.managementsystem.model.user.response.UserCreateResponse;
import az.hamburg.managementsystem.model.user.response.UserReadResponse;
import az.hamburg.managementsystem.model.user.response.UserUpdateResponse;
import az.hamburg.managementsystem.repository.UserRepository;
import az.hamburg.managementsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

        private final UserRepository userRepository;
        private final UserMapper userMapper;


    @Override
    public UserCreateResponse create(UserCreateRequest createRequest) {
        User user = userMapper.createRequestToEntity(createRequest);
        user.setRoleType(RoleType.USER);
        user.setCreatedBy(createRequest.getUsername());
        userRepository.save(user);
        return  userMapper.entityToCreateResponse(user);
    }

    @Override
    public UserReadResponse getId(Long id) {
        User foundedUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(ErrorMessage.USER_NOT_FOUND, HttpStatus.NOT_FOUND.name()));

        return userMapper.entityToReadResponse(foundedUser);

    }

    @Override
    public List<UserReadResponse> getAll() {
        List<User> userList = userRepository.findAll();

        return userList.stream()
                .map(userMapper::entityToReadResponse)
                .toList();
    }

    @Override
    public UserUpdateResponse update(Long id, UserUpdateRequest updateRequest) {
        User foundedUser = userRepository
                .findById(id).orElseThrow(() -> new UserNotFoundException(ErrorMessage.USER_NOT_FOUND, HttpStatus.NOT_FOUND.name()));
        User savedUser = userMapper.updateRequestToEntity(updateRequest);
        savedUser.setId(foundedUser.getId());
        userRepository.save(savedUser);

        return userMapper.entityToUpdateResponse(savedUser);
    }

    @Override
    public void delete(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(ErrorMessage.USER_NOT_FOUND, HttpStatus.NOT_FOUND.name()));
        userRepository.deleteById(user.getId());
    }
}
