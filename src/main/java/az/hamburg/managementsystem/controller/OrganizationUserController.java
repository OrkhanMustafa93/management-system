package az.hamburg.managementsystem.controller;

import az.hamburg.managementsystem.model.organizationuser.request.OrganizationUserCreateRequest;
import az.hamburg.managementsystem.model.organizationuser.request.OrganizationUserUpdateRequest;
import az.hamburg.managementsystem.model.organizationuser.response.OrganizationUserCreateResponse;
import az.hamburg.managementsystem.model.organizationuser.response.OrganizationUserReadResponse;
import az.hamburg.managementsystem.model.organizationuser.response.OrganizationUserUpdateResponse;
import az.hamburg.managementsystem.service.OrganizationUserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/organization-users")
@RequiredArgsConstructor
@Tag(name = "OrganizationUser Controller API", description = "Managing OrganizationUser Apis")
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class OrganizationUserController {

    private final OrganizationUserService organizationUserService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrganizationUserCreateResponse create(@RequestBody OrganizationUserCreateRequest createRequest) {
        return organizationUserService.create(createRequest);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public OrganizationUserReadResponse getId(@PathVariable Long id) {

        return organizationUserService.getId(id);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<OrganizationUserReadResponse> getAll() {

        return organizationUserService.getAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {

        organizationUserService.delete(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public OrganizationUserUpdateResponse update(@PathVariable Long id, @RequestBody OrganizationUserUpdateRequest updateRequest) {
        return organizationUserService.update(id, updateRequest);
    }
}
