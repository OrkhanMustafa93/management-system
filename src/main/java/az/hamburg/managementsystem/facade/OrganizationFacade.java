package az.hamburg.managementsystem.facade;

import az.hamburg.managementsystem.model.organization.request.OrganizationCreateDetailRequest;
import az.hamburg.managementsystem.model.organization.response.OrganizationCreateDetailResponse;
import az.hamburg.managementsystem.model.organization.response.OrganizationReadDetailResponse;
import org.springframework.stereotype.Service;

public interface OrganizationFacade {

    OrganizationCreateDetailResponse createDetail(OrganizationCreateDetailRequest request,Long userId);

    OrganizationReadDetailResponse getByIdOrganizationDetail(Long id);
}
