package az.hamburg.managementsystem.service;

import az.hamburg.managementsystem.domain.User;
import az.hamburg.managementsystem.model.organization.request.OrganizationCreateRequest;
import az.hamburg.managementsystem.model.organization.request.OrganizationUpdateRequest;
import az.hamburg.managementsystem.model.organization.response.OrganizationCreateResponse;
import az.hamburg.managementsystem.model.organization.response.OrganizationReadResponse;
import az.hamburg.managementsystem.model.organization.response.OrganizationUpdateResponse;

import java.util.List;

public interface OrganizationService {

    OrganizationCreateResponse create(OrganizationCreateRequest createRequest, Long userId);

    OrganizationReadResponse getId(Long id);

    List<OrganizationReadResponse> getAll();

    OrganizationUpdateResponse update(Long id , OrganizationUpdateRequest updateRequest);

    void delete (Long id, Long userId);

}