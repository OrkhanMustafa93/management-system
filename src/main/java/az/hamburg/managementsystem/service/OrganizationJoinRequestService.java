package az.hamburg.managementsystem.service;

import az.hamburg.managementsystem.domain.OrganizationJoinRequest;
import az.hamburg.managementsystem.model.dto.OrganizationJoinRequestUpdateStatus;
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

    OrganizationJoinRequestUpdateResponse update(OrganizationJoinRequestUpdateRequest updateRequest);

    void delete (Long id);

    List<OrganizationJoinRequestReadResponse> getAllStatusPending();

    List<OrganizationJoinRequestReadResponse> getAllByOrganizationId(Long organizationId);

    List<OrganizationJoinRequestReadResponse> getAllByOrganizationIdAndStatus(Long organizationId);



}
