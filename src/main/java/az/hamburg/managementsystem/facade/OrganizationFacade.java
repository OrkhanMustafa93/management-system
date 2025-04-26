package az.hamburg.managementsystem.facade;

import az.hamburg.managementsystem.SelectIds;
import az.hamburg.managementsystem.domain.Organization;
import az.hamburg.managementsystem.model.dto.OrganizationStatusReadResponse;
import az.hamburg.managementsystem.model.organization.request.OrganizationCreateDetailRequest;
import az.hamburg.managementsystem.model.organization.request.OrganizationUpdateDetailRequest;
import az.hamburg.managementsystem.model.organization.response.OrganizationCreateDetailResponse;
import az.hamburg.managementsystem.model.organization.response.OrganizationReadDetailResponse;
import az.hamburg.managementsystem.model.organization.response.OrganizationUpdateDetailResponse;
import az.hamburg.managementsystem.model.user.response.UserReadResponse;
import org.springframework.stereotype.Service;

import java.util.List;

public interface OrganizationFacade {

    OrganizationCreateDetailResponse createDetail(OrganizationCreateDetailRequest request,Long userId);

    OrganizationReadDetailResponse getByIdOrganizationDetail(Long id);

     OrganizationUpdateDetailResponse updateDetail(Long userId ,Long organizationId,OrganizationUpdateDetailRequest updateDetailRequest);

    void  deleteOrganizations(Long userId, SelectIds selectIds);

    OrganizationStatusReadResponse updateStatus(Long userId, Long organizationId, boolean status);

   List<OrganizationReadDetailResponse> getAllOrganizationsStatusTrue();

   List<OrganizationReadDetailResponse> getAll();
}
