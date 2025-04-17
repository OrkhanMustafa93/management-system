package az.hamburg.managementsystem.controller;

import az.hamburg.managementsystem.facade.OrganizationFacade;
import az.hamburg.managementsystem.facade.impl.OrganizationFacadeImpl;
import az.hamburg.managementsystem.model.organization.request.OrganizationCreateDetailRequest;
import az.hamburg.managementsystem.model.organization.request.OrganizationCreateRequest;
import az.hamburg.managementsystem.model.organization.request.OrganizationUpdateRequest;
import az.hamburg.managementsystem.model.organization.response.*;
import az.hamburg.managementsystem.service.OrganizationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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
    private final OrganizationFacadeImpl organizationFacadeImpl;

    @PostMapping("/userId/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public OrganizationCreateResponse create(@Valid  @RequestBody OrganizationCreateRequest createRequest) {
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

    @DeleteMapping("/{organizationId}/userId/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable(name = "organizationId") Long organizationId, @PathVariable(name = "id") Long id) {
        organizationService.delete(organizationId, id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public OrganizationUpdateResponse update(@PathVariable Long id, @RequestBody OrganizationUpdateRequest updateRequest) {
        return organizationService.update(id, updateRequest);
    }

    @PostMapping("/detail/userId/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public OrganizationCreateDetailResponse createDetail(@PathVariable Long id , @RequestBody OrganizationCreateDetailRequest request){
        return organizationFacadeImpl.createDetail(request ,id);
    }
    
}
