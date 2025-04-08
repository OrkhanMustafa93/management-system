package az.hamburg.managementsystem.service;

import az.hamburg.managementsystem.model.contactlink.request.ContactLinkCreateRequest;
import az.hamburg.managementsystem.model.contactlink.request.ContactLinkUpdateRequest;
import az.hamburg.managementsystem.model.contactlink.response.ContactLinkCreateResponse;
import az.hamburg.managementsystem.model.contactlink.response.ContactLinkReadResponse;
import az.hamburg.managementsystem.model.contactlink.response.ContactLinkUpdateResponse;

import java.util.List;

public interface ContactLinkService {

    ContactLinkCreateResponse create(ContactLinkCreateRequest createRequest);

    ContactLinkUpdateResponse update(Long id, ContactLinkUpdateRequest updateRequest);

    ContactLinkReadResponse getById(Long id);

    List<ContactLinkReadResponse> getAll();

    void  delete(Long id);
}
