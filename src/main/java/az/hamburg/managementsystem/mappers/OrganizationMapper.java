package az.hamburg.managementsystem.mappers;

import az.hamburg.managementsystem.domain.Organization;
import az.hamburg.managementsystem.model.dto.OrganizationStatusReadResponse;
import az.hamburg.managementsystem.model.organization.request.OrganizationCreateDetailRequest;
import az.hamburg.managementsystem.model.organization.request.OrganizationCreateRequest;
import az.hamburg.managementsystem.model.organization.request.OrganizationUpdateDetailRequest;
import az.hamburg.managementsystem.model.organization.request.OrganizationUpdateRequest;
import az.hamburg.managementsystem.model.organization.response.*;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface
OrganizationMapper {

    Organization createRequestToEntity (OrganizationCreateRequest createRequest);

    OrganizationCreateResponse entityToCreateResponse (Organization organization);

    OrganizationReadResponse entityToReadResponse(Organization organization);

    Organization updateRequestToEntity(OrganizationUpdateRequest updateRequest);

    OrganizationUpdateResponse entityToUpdateResponse(Organization organization);

    OrganizationCreateDetailResponse entityToOrganizationCreateDetailResponse(Organization entity);

    OrganizationReadDetailResponse entityToOrganizationReadDetailResponse(Organization entity);

    Organization organizationCreateDetailRequestToEntity(OrganizationCreateDetailRequest detailRequest);


   Organization updateDetailRequestToEntity(OrganizationUpdateDetailRequest detailRequest);

    OrganizationUpdateDetailResponse entityToUpdateDetailResponse(Organization organization);

    OrganizationStatusReadResponse entityToOrganizationStatusReadResponse(Organization organization);

   OrganizationReadDetailResponse readResponseToReadDetail(OrganizationReadResponse readResponse);
}
