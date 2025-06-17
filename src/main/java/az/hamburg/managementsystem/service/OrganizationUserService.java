package az.hamburg.managementsystem.service;

import az.hamburg.managementsystem.model.organizationuser.request.OrganizationUserCreateRequest;
import az.hamburg.managementsystem.model.organizationuser.request.OrganizationUserUpdateRequest;
import az.hamburg.managementsystem.model.organizationuser.response.OrganizationUserCreateResponse;
import az.hamburg.managementsystem.model.organizationuser.response.OrganizationUserReadResponse;
import az.hamburg.managementsystem.model.organizationuser.response.OrganizationUserUpdateResponse;

import java.util.List;

public interface OrganizationUserService {
    OrganizationUserCreateResponse create(OrganizationUserCreateRequest createRequest);

    OrganizationUserReadResponse getId(Long id);

    List<OrganizationUserReadResponse> getAll();

    OrganizationUserUpdateResponse update(Long id , OrganizationUserUpdateRequest updateRequest);

    void delete (Long userId);

}
