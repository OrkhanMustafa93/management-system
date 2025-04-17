package az.hamburg.managementsystem.mappers;

import az.hamburg.managementsystem.domain.OrganizationJoinRequest;
import az.hamburg.managementsystem.model.organizationjoinrequest.request.OrganizationJoinRequestCreateRequest;
import az.hamburg.managementsystem.model.organizationjoinrequest.request.OrganizationJoinRequestUpdateRequest;
import az.hamburg.managementsystem.model.organizationjoinrequest.response.OrganizationJoinRequestCreateResponse;
import az.hamburg.managementsystem.model.organizationjoinrequest.response.OrganizationJoinRequestReadResponse;
import az.hamburg.managementsystem.model.organizationjoinrequest.response.OrganizationJoinRequestUpdateResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface OrganizationJoinRequestMapper {

    OrganizationJoinRequest createRequestToEntity(OrganizationJoinRequestCreateRequest createRequest);

    OrganizationJoinRequestCreateResponse entityToCreateResponse(OrganizationJoinRequest organizationJoinRequest);

    OrganizationJoinRequestReadResponse entityToReadResponse(OrganizationJoinRequest organizationJoinRequest);

    OrganizationJoinRequest updateRequestToEntity(@MappingTarget OrganizationJoinRequest organizationJoinRequest, OrganizationJoinRequestUpdateRequest updateRequest);

    OrganizationJoinRequestUpdateResponse entityToUpdateResponse(OrganizationJoinRequest organizationJoinRequest);
}
