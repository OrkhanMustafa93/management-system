package az.hamburg.managementsystem.mappers;

import az.hamburg.managementsystem.domain.ContactLink;
import az.hamburg.managementsystem.model.contactlink.request.ContactLinkCreateRequest;
import az.hamburg.managementsystem.model.contactlink.request.ContactLinkUpdateRequest;
import az.hamburg.managementsystem.model.contactlink.response.ContactLinkCreateResponse;
import az.hamburg.managementsystem.model.contactlink.response.ContactLinkReadResponse;
import az.hamburg.managementsystem.model.contactlink.response.ContactLinkUpdateResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ContactLinkMapper {

    ContactLink createRequestToEntity(ContactLinkCreateRequest createRequest);

    ContactLinkCreateResponse entityToCreateResponse(ContactLink contactLink);

    ContactLink updateRequestToEntity(@MappingTarget ContactLink contactLink, ContactLinkUpdateRequest updateRequest);

    ContactLinkUpdateResponse entityToUpdateResponse(ContactLink contactLink);

    ContactLinkReadResponse entityToReadResponse(ContactLink contactLink);

}
