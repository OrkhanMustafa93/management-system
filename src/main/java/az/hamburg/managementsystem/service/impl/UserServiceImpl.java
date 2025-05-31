package az.hamburg.managementsystem.service.impl;
import az.hamburg.managementsystem.common.SelectIds;
import az.hamburg.managementsystem.domain.OrganizationUser;
import az.hamburg.managementsystem.domain.RoleType;
import az.hamburg.managementsystem.domain.User;
import az.hamburg.managementsystem.exception.error.ErrorMessage;
import az.hamburg.managementsystem.exception.handler.UserNotFoundException;
import az.hamburg.managementsystem.exception.handler.UserUnAuthorizedException;
import az.hamburg.managementsystem.exception.handler.UsersWithTheseIdsNotFoundException;
import az.hamburg.managementsystem.exception.handler.WrongPhoneNumberException;
import az.hamburg.managementsystem.mappers.UserMapper;
import az.hamburg.managementsystem.model.user.request.UserCreateRequest;
import az.hamburg.managementsystem.model.user.request.UserUpdateRequest;
import az.hamburg.managementsystem.model.user.response.*;
import az.hamburg.managementsystem.repository.OrganizationUserRepository;
import az.hamburg.managementsystem.repository.UserRepository;
import az.hamburg.managementsystem.service.UserService;
import ch.qos.logback.core.status.Status;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

        private final UserRepository userRepository;
        private final UserMapper userMapper;
        private final OrganizationUserRepository organizationUserRepository;

    @Override
    public UserCreateResponse create(UserCreateRequest createRequest) {
        String phoneNumber = createRequest.getPhoneNumber();
        String checkNumber = phoneNumber.substring(0, 4);
        List<String> operatorBeginningsOfAzerbaijan = Arrays.asList("50", "51", "55", "99", "70", "77", "60");
        String checkOperatorNumber = phoneNumber.substring(4, 6);
        if (!checkNumber.equals("+994") || !operatorBeginningsOfAzerbaijan.contains(checkOperatorNumber)) {
            throw new WrongPhoneNumberException(ErrorMessage.NUMBER_IS_WRONG, HttpStatus.BAD_REQUEST.name());
        }
            User user = userMapper.createRequestToEntity(createRequest);
            user.setRoleType(RoleType.USER);
            user.setCreatedBy(createRequest.getUsername());
            userRepository.save(user);
            return userMapper.entityToCreateResponse(user);
    }

    @Override
    public List<UserReadResponse> getByIds(SelectIds selectIds) {
        List<Long> ids = selectIds.getIds();
        List<Long> notFoundedIds = new ArrayList<>();
        SelectIds selectIds1 = new SelectIds();
        selectIds1.setIds(notFoundedIds);
        for (Long id : ids) {
            if (!userRepository.existsById(id)) {
                notFoundedIds.add(id);
            }
        }
        if (!notFoundedIds.isEmpty()) {
            throw new UsersWithTheseIdsNotFoundException(selectIds1);
        }
        List<UserReadResponse> result = new ArrayList<>();
        for (Long id: ids){
            UserReadResponse response = getId(id);
            result.add(response);
        }
        return result;
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

    @Override
    public UserRoleUpdateResponse roleUpdate(Long id, Long changerId, RoleType roleType) {
        User foundedUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(ErrorMessage.USER_NOT_FOUND, HttpStatus.NOT_FOUND.name()));

        User changerUser = userRepository.findById(changerId)
                .orElseThrow(() -> new UserNotFoundException(ErrorMessage.USER_NOT_FOUND, HttpStatus.NOT_FOUND.name()));

        if (!changerUser.getRoleType().equals(RoleType.ADMIN)) {
            throw new UserUnAuthorizedException(ErrorMessage.USERUNAUTHORIZED, HttpStatus.UNAUTHORIZED.name());
        }


        foundedUser.setModifiedBy(changerUser.getUsername());
        foundedUser.setRoleType(roleType);
        userRepository.save(foundedUser);

        return userMapper.entityToUserRoleUpdateResponse(foundedUser);    }

    @Override
    public UserStatusUpdateResponse statusUpdate(Long id, Long changerId, boolean status) {
        User foundedUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(ErrorMessage.USER_NOT_FOUND, HttpStatus.NOT_FOUND.name()));

        User changerUser = userRepository.findById(changerId)
                .orElseThrow(() -> new UserNotFoundException(ErrorMessage.USER_NOT_FOUND, HttpStatus.NOT_FOUND.name()));

        if (!changerUser.getRoleType().equals(RoleType.MODERATOR)) {
            throw new UserUnAuthorizedException(ErrorMessage.USERUNAUTHORIZED, HttpStatus.UNAUTHORIZED.name());
        }

        foundedUser.setStatus(status);

        foundedUser.setModifiedBy(changerUser.getUsername());

        userRepository.save(foundedUser);

        return userMapper.entityToUserStatusUpdateResponse(foundedUser);
    }

    @Override
    public List<UserReadResponse> getAllUserByOrganizationId(Long organizationId) {
        List<OrganizationUser> organizationUsers = organizationUserRepository.getAllByOrganizationId(organizationId);
        List<UserReadResponse> result = new ArrayList<>();

        for (OrganizationUser organizationUser : organizationUsers) {
            Long userId = organizationUser.getUserId();
            UserReadResponse user = getId(userId);
            result.add(user);
        }
        return result;

    }

}
