package az.hamburg.managementsystem.service;

import az.hamburg.managementsystem.model.request.OrganizationCreateRequest;
import az.hamburg.managementsystem.model.request.OrganizationUpdateRequest;
import az.hamburg.managementsystem.model.response.OrganizationCreateResponse;
import az.hamburg.managementsystem.model.response.OrganizationReadResponse;
import az.hamburg.managementsystem.model.response.OrganizationUpdateResponse;

import java.util.List;

public interface OrganizationService {

    OrganizationCreateResponse create(OrganizationCreateRequest createRequest);

    OrganizationReadResponse getId(Long id);

    List<OrganizationReadResponse> getAll();

    OrganizationUpdateResponse update(Long id , OrganizationUpdateRequest updateRequest);

    void delete (Long id);
}
