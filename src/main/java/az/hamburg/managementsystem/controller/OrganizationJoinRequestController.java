package az.hamburg.managementsystem.controller;

import az.hamburg.managementsystem.model.organizationjoinrequest.request.OrganizationJoinRequestCreateRequest;
import az.hamburg.managementsystem.model.organizationjoinrequest.request.OrganizationJoinRequestUpdateRequest;
import az.hamburg.managementsystem.model.organizationjoinrequest.response.OrganizationJoinRequestCreateResponse;
import az.hamburg.managementsystem.model.organizationjoinrequest.response.OrganizationJoinRequestReadResponse;
import az.hamburg.managementsystem.model.organizationjoinrequest.response.OrganizationJoinRequestUpdateResponse;
import az.hamburg.managementsystem.service.OrganizationJoinRequestService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/organization-join-request")
@RequiredArgsConstructor
@Tag(name = "OrganizationJoinRequest Controller API", description = "Managing OrganizationJoinRequest Apis")
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class OrganizationJoinRequestController {

    private final OrganizationJoinRequestService organizationJoinRequestService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrganizationJoinRequestCreateResponse create(@RequestBody OrganizationJoinRequestCreateRequest request) {
        return organizationJoinRequestService.create(request);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public OrganizationJoinRequestReadResponse getId(@PathVariable Long id) {
        return organizationJoinRequestService.getId(id);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<OrganizationJoinRequestReadResponse> getAll() {
        return organizationJoinRequestService.getAll();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public OrganizationJoinRequestUpdateResponse update(@PathVariable Long id, @RequestBody OrganizationJoinRequestUpdateRequest request) {
        return organizationJoinRequestService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        organizationJoinRequestService.delete(id);
    }

    @GetMapping("/status")
    @ResponseStatus(HttpStatus.OK)
    public List<OrganizationJoinRequestReadResponse> getPendingStatus() {
        return organizationJoinRequestService.getAllStatusPending();
    }

    @GetMapping("/organization/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<OrganizationJoinRequestReadResponse> getAllByOrganizationId(@PathVariable Long id) {
        return organizationJoinRequestService.getAllByOrganizationId(id);
    }
}

