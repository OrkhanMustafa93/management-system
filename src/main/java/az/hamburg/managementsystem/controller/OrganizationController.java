package az.hamburg.managementsystem.controller;

import az.hamburg.managementsystem.common.SelectIds;
import az.hamburg.managementsystem.facade.OrganizationFacade;
import az.hamburg.managementsystem.model.dto.OrganizationStatusReadResponse;
import az.hamburg.managementsystem.model.organization.request.OrganizationCreateDetailRequest;
import az.hamburg.managementsystem.model.organization.request.OrganizationCreateRequest;
import az.hamburg.managementsystem.model.organization.request.OrganizationUpdateDetailRequest;
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
    private final OrganizationFacade organizationFacade;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public OrganizationCreateResponse create(@Valid  @RequestBody OrganizationCreateRequest createRequest) {
        return organizationService.create(createRequest);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public OrganizationReadDetailResponse getId(@PathVariable Long id) {
        return organizationFacade.getByIdOrganizationDetail(id);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<OrganizationReadDetailResponse> getAll() {
        return organizationFacade.getAll();
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
        return organizationFacade.createDetail(request ,id);
    }

    @PutMapping("/{userId}/detailUserId/{organizationId}")
    @ResponseStatus(HttpStatus.OK)
    public OrganizationUpdateDetailResponse updateDetail(@PathVariable Long userId,@PathVariable Long organizationId, @RequestBody OrganizationUpdateDetailRequest updateRequest) {
        return organizationFacade.updateDetail(userId,organizationId,updateRequest);
    }

    @DeleteMapping("userId/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrganizations(@PathVariable Long userId, SelectIds request) {
        organizationFacade.deleteOrganizations(userId,request);
    }

    @PutMapping("/{userId}/status-update/{organizationId}")
    @ResponseStatus(HttpStatus.OK)
    public OrganizationStatusReadResponse updateStatus(@PathVariable Long userId, @PathVariable Long organizationId, @RequestParam boolean status) {
        return organizationFacade.updateStatus(userId,organizationId,status);
    }

    @GetMapping("status-true")
    @ResponseStatus(HttpStatus.OK)
    public List<OrganizationReadDetailResponse> getOrganizationsStatus() {
        return organizationFacade.getAllOrganizationsStatusTrue();
    }
}
