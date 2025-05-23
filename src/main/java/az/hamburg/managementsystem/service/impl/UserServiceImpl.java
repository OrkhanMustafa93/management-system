package az.hamburg.managementsystem.service.impl;
import az.hamburg.managementsystem.domain.RoleType;
import az.hamburg.managementsystem.domain.User;
import az.hamburg.managementsystem.exception.error.ErrorMessage;
import az.hamburg.managementsystem.exception.handler.UserNotFoundException;
import az.hamburg.managementsystem.exception.handler.UserUnAuthorizedException;
import az.hamburg.managementsystem.exception.handler.WrongPhoneNumberException;
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
        String phoneNumber = createRequest.getPhoneNumber();
        String checkNumber = phoneNumber.substring(0, 4);
        if (!checkNumber.equals("+994")) {
            throw new WrongPhoneNumberException(ErrorMessage.NUMBER_IS_WRONG, HttpStatus.BAD_REQUEST.name());
        }
            User user = userMapper.createRequestToEntity(createRequest);
            user.setRoleType(RoleType.USER);
            user.setCreatedBy(createRequest.getUsername());
            userRepository.save(user);
            return userMapper.entityToCreateResponse(user);
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
//        savedUser.setModified(foundedUser.getModified());
        savedUser.setModifiedBy(updateRequest.getUsername());
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

//    @Override
//    public UserUpdateResponse statusUpdate(Long id, Long userId) {
//        User foundedUser = userRepository.findById(id)
//                .orElseThrow(() -> new UserNotFoundException(ErrorMessage.USER_NOT_FOUND, HttpStatus.NOT_FOUND.name()));
//        UserReadResponse userReadResponse = userService.getId(userId);
//        if (!userReadResponse.getRoleType().equals(RoleType.MODERATOR)) {
//            throw new UserUnAuthorizedException(ErrorMessage.USERUNAUTHORIZED, HttpStatus.UNAUTHORIZED.name());
//        }
//        foundedUser.setStatus(true);
//        userRepository.save(foundedUser);
//
//        return userMapper.entityToUpdateResponse(foundedUser);
//    }

//    @Override
//    public UserUpdateResponse roleUpdate(Long id, RoleType roleType, Long userId) {
//        return null;
//    }

//    @Override
//    public UserUpdateResponse roleUpdate(Long id, UserUpdateRequest userUpdateRequest, Long userId) {
//        User foundedUser = userRepository.findById(id)
//                .orElseThrow(() -> new UserNotFoundException(ErrorMessage.USER_NOT_FOUND, HttpStatus.NOT_FOUND.name()));
//        UserReadResponse userReadResponse = userService.getId(userId);
//        if (!userReadResponse.getRoleType().equals(RoleType.ADMIN)) {
//            throw new UserUnAuthorizedException(ErrorMessage.USERUNAUTHORIZED, HttpStatus.UNAUTHORIZED.name());
//        }
//        foundedUser.setRoleType(RoleType.MODERATOR);
//        userRepository.save(foundedUser);
//
//        return userMapper.entityToUpdateResponse(foundedUser);}

}
