package az.hamburg.managementsystem.mappers;

import az.hamburg.managementsystem.domain.OrganizationUser;
import az.hamburg.managementsystem.model.organizationuser.request.OrganizationUserCreateRequest;
import az.hamburg.managementsystem.model.organizationuser.request.OrganizationUserUpdateRequest;
import az.hamburg.managementsystem.model.organizationuser.response.OrganizationUserCreateResponse;
import az.hamburg.managementsystem.model.organizationuser.response.OrganizationUserReadResponse;
import az.hamburg.managementsystem.model.organizationuser.response.OrganizationUserUpdateResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface OrganizationUserMapper {
    OrganizationUser createRequestToEntity(OrganizationUserCreateRequest createRequest);

    OrganizationUserCreateResponse entityToCreateResponse(OrganizationUser organizationUser);

    OrganizationUserReadResponse entityToReadResponse(OrganizationUser organizationUser);

    OrganizationUser updateRequestToEntity(@MappingTarget OrganizationUser organizationUser, OrganizationUserUpdateRequest updateRequest);

    OrganizationUserUpdateResponse entityToUpdateResponse(OrganizationUser organizationUser);
}
