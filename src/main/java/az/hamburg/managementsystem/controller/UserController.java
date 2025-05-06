package az.hamburg.managementsystem.controller;
import az.hamburg.managementsystem.common.SelectIds;
import az.hamburg.managementsystem.domain.RoleType;
import az.hamburg.managementsystem.model.user.request.UserCreateRequest;
import az.hamburg.managementsystem.model.user.request.UserUpdateRequest;
import az.hamburg.managementsystem.model.user.response.*;
import az.hamburg.managementsystem.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/users")
@RequiredArgsConstructor
@Tag(name = "User Controller API", description = "Managing User Apis")
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserCreateResponse create(@Valid @RequestBody UserCreateRequest createRequest) {
        return userService.create(createRequest);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserReadResponse getId(@PathVariable Long id) {
        return userService.getId(id);
    }

//    @GetMapping()
//    @ResponseStatus(HttpStatus.OK)
//    public List<UserReadResponse> getAll() {
//        return userService.getAll();
//    }

    @PostMapping("/users-by-ids")
    public List<UserReadResponse> getUsersByIds(@RequestBody SelectIds selectIds) {
        return userService.getByIds(selectIds);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        userService.delete(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserUpdateResponse update(@PathVariable Long id, @RequestBody UserUpdateRequest updateRequest) {
        return userService.update(id, updateRequest);
    }

    //!!!!!!!!!!!!
//    @PutMapping("/{id}/status-update/{userId}")
//    @ResponseStatus(HttpStatus.OK)
//    public UserUpdateResponse statusUpdate(@PathVariable Long id, @PathVariable Long userId) {
//        return userService.statusUpdate(id, userId);
//    }

    @PutMapping("/{id}/role-update/{changerId}")
    @ResponseStatus(HttpStatus.OK)
    public UserRoleUpdateResponse updateRole(@PathVariable Long id, @PathVariable Long changerId, @RequestParam RoleType roleType) {
        return userService.roleUpdate(id, changerId, roleType);
    }

    @PutMapping("/{id}/status-update/{changerId}")
    @ResponseStatus(HttpStatus.OK)
    public UserStatusUpdateResponse updateStatus(@PathVariable Long id, @PathVariable Long changerId, @RequestParam boolean status) {
        return userService.statusUpdate(id, changerId, status);
    }

    @GetMapping("/organization/{organizationId}")
    @ResponseStatus(HttpStatus.OK)
    public List<UserReadResponse> getAllUserByOrganizationId(@PathVariable (name = "organizationId") Long organizationId) {
        return userService.getAllUserByOrganizationId(organizationId);
    }

}
