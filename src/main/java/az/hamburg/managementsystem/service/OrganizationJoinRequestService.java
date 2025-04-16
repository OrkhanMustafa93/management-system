package az.hamburg.managementsystem.service;

import az.hamburg.managementsystem.model.organizationjoinrequest.request.OrganizationJoinRequestCreateRequest;
import az.hamburg.managementsystem.model.organizationjoinrequest.request.OrganizationJoinRequestUpdateRequest;
import az.hamburg.managementsystem.model.organizationjoinrequest.response.OrganizationJoinRequestCreateResponse;
import az.hamburg.managementsystem.model.organizationjoinrequest.response.OrganizationJoinRequestReadResponse;
import az.hamburg.managementsystem.model.organizationjoinrequest.response.OrganizationJoinRequestUpdateResponse;

import java.util.List;

public interface OrganizationJoinRequestService {

    OrganizationJoinRequestCreateResponse create(OrganizationJoinRequestCreateRequest createRequest);

    OrganizationJoinRequestReadResponse getId(Long id);

    List<OrganizationJoinRequestReadResponse> getAll();

    OrganizationJoinRequestUpdateResponse update(Long id , OrganizationJoinRequestUpdateRequest updateRequest);

    void delete (Long id);
}
