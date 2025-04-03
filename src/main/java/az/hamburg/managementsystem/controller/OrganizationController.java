package az.hamburg.managementsystem.controller;

import az.hamburg.managementsystem.model.request.OrganizationCreateRequest;
import az.hamburg.managementsystem.model.request.OrganizationUpdateRequest;
import az.hamburg.managementsystem.model.response.OrganizationCreateResponse;
import az.hamburg.managementsystem.model.response.OrganizationReadResponse;
import az.hamburg.managementsystem.model.response.OrganizationUpdateResponse;
import az.hamburg.managementsystem.service.OrganizationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/organizations")
@RequiredArgsConstructor
@Tag(name = "Organization Controller API", description = "Managing Organization Apis")
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class OrganizationController {
    
    private final OrganizationService organizationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrganizationCreateResponse create(@RequestBody OrganizationCreateRequest createRequest) {
        return organizationService.create(createRequest);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public OrganizationReadResponse getId(@PathVariable Long id) {
        return organizationService.getId(id);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<OrganizationReadResponse> getAll() {
        return organizationService.getAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        organizationService.delete(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public OrganizationUpdateResponse update(@PathVariable Long id, @RequestBody OrganizationUpdateRequest updateRequest) {
        return organizationService.update(id, updateRequest);
    }
    
}
