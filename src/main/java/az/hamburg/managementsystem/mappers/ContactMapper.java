package az.hamburg.managementsystem.mappers;

import az.hamburg.managementsystem.domain.Contact;
import az.hamburg.managementsystem.domain.ContactLink;
import az.hamburg.managementsystem.model.address.response.AddressReadResponse;
import az.hamburg.managementsystem.model.contact.request.ContactCreateRequest;
import az.hamburg.managementsystem.model.contact.request.ContactUpdateRequest;
import az.hamburg.managementsystem.model.contact.response.ContactCreateResponse;
import az.hamburg.managementsystem.model.contact.response.ContactReadResponse;
import az.hamburg.managementsystem.model.contact.response.ContactUpdateResponse;

import az.hamburg.managementsystem.model.organization.response.OrganizationCreateDetailResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ContactMapper {

    Contact createRequestToEntity(ContactCreateRequest createRequest);

    ContactCreateResponse entityToCreateResponse(Contact contact);

    ContactReadResponse entityToReadResponse(Contact contact);

    Contact updateRequestToEntity(@MappingTarget Contact contact , ContactUpdateRequest updateRequest);

    ContactUpdateResponse entityToUpdateResponse(Contact contact);

}
