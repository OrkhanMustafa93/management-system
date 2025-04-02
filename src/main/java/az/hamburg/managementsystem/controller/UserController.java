package az.hamburg.managementsystem.controller;
import az.hamburg.managementsystem.model.request.UserCreateRequest;
import az.hamburg.managementsystem.model.request.UserUpdateRequest;
import az.hamburg.managementsystem.model.response.UserCreateResponse;
import az.hamburg.managementsystem.model.response.UserReadResponse;
import az.hamburg.managementsystem.model.response.UserUpdateResponse;
import az.hamburg.managementsystem.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
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
    public UserCreateResponse create(@RequestBody UserCreateRequest createRequest) {
        return userService.create(createRequest);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserReadResponse getId(@PathVariable Long id) {
        return userService.getId(id);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<UserReadResponse> getAll() {
        return userService.getAll();
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
}
