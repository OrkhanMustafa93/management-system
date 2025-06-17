package az.hamburg.managementsystem.mappers;

import az.hamburg.managementsystem.domain.ContactLink;
import az.hamburg.managementsystem.model.contact.response.ContactReadResponse;
import az.hamburg.managementsystem.model.contactlink.request.ContactLinkCreateRequest;
import az.hamburg.managementsystem.model.contactlink.request.ContactLinkUpdateRequest;
import az.hamburg.managementsystem.model.contactlink.response.ContactLinkCreateResponse;
import az.hamburg.managementsystem.model.contactlink.response.ContactLinkReadResponse;
import az.hamburg.managementsystem.model.contactlink.response.ContactLinkUpdateResponse;
import az.hamburg.managementsystem.model.dto.ContactLinkDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ContactLinkMapper {

    ContactLink createRequestToEntity(ContactLinkCreateRequest createRequest);

    ContactLinkCreateResponse entityToCreateResponse(ContactLink contactLink);

    ContactLink updateRequestToEntity(@MappingTarget ContactLink contactLink, ContactLinkUpdateRequest updateRequest);

    ContactLinkUpdateResponse entityToUpdateResponse(ContactLink contactLink);

    ContactLinkReadResponse entityToReadResponse(ContactLink contactLink);

     ContactLinkDTO entityToContactLinkDto(ContactLink contactLink);




}
