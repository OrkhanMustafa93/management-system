package az.hamburg.managementsystem.facade;

import az.hamburg.managementsystem.model.organization.request.OrganizationCreateDetailRequest;
import az.hamburg.managementsystem.model.organization.response.OrganizationCreateDetailResponse;

public interface OrganizationFacade {

    OrganizationCreateDetailResponse createDetail(OrganizationCreateDetailRequest request,Long userId);

}
