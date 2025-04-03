package az.hamburg.managementsystem.mappers;

import az.hamburg.managementsystem.domain.Organization;
import az.hamburg.managementsystem.model.request.OrganizationCreateRequest;
import az.hamburg.managementsystem.model.request.OrganizationUpdateRequest;
import az.hamburg.managementsystem.model.response.OrganizationCreateResponse;
import az.hamburg.managementsystem.model.response.OrganizationReadResponse;
import az.hamburg.managementsystem.model.response.OrganizationUpdateResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface OrganizationMapper {

    Organization createRequestToEntity (OrganizationCreateRequest createRequest);

    OrganizationCreateResponse entityToCreateResponse (Organization organization);

//    List<OrganizationReadResponse> listEntityToListReadResponse(List<Organization> organizations);

    OrganizationReadResponse entityToReadResponse(Organization organization);

    Organization updateRequestToEntity(OrganizationUpdateRequest updateRequest);

    OrganizationUpdateResponse entityToUpdateResponse(Organization organization);

}
