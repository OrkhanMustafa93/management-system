package az.hamburg.managementsystem.service;

import az.hamburg.managementsystem.model.contact.request.ContactCreateRequest;
import az.hamburg.managementsystem.model.contact.request.ContactUpdateRequest;
import az.hamburg.managementsystem.model.contact.response.ContactCreateResponse;
import az.hamburg.managementsystem.model.contact.response.ContactReadResponse;
import az.hamburg.managementsystem.model.contact.response.ContactUpdateResponse;


import java.util.List;

public interface ContactService {

    ContactCreateResponse create(ContactCreateRequest createRequest);

    ContactReadResponse getId(Long id);

    List<ContactReadResponse> getAll();

    ContactUpdateResponse update(Long id , ContactUpdateRequest updateRequest);

    void delete (Long id);
}
