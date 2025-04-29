package az.hamburg.managementsystem.service.impl;

import az.hamburg.managementsystem.domain.ContactLink;
import az.hamburg.managementsystem.exception.error.ErrorMessage;
import az.hamburg.managementsystem.exception.handler.ContactLinkNotFoundException;
import az.hamburg.managementsystem.mappers.ContactLinkMapper;
import az.hamburg.managementsystem.model.contactlink.request.ContactLinkCreateRequest;
import az.hamburg.managementsystem.model.contactlink.request.ContactLinkUpdateRequest;
import az.hamburg.managementsystem.model.contactlink.response.ContactLinkCreateResponse;
import az.hamburg.managementsystem.model.contactlink.response.ContactLinkReadResponse;
import az.hamburg.managementsystem.model.contactlink.response.ContactLinkUpdateResponse;
import az.hamburg.managementsystem.repository.ContactLinkRepository;
import az.hamburg.managementsystem.repository.ContactRepository;
import az.hamburg.managementsystem.service.ContactLinkService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ContactLinkServiceImpl implements ContactLinkService {

    private final ContactLinkMapper contactLinkMapper;
    private final ContactLinkRepository contactLinkRepository;
    private final ContactRepository contactRepository;

    @Override
    public ContactLinkCreateResponse create(ContactLinkCreateRequest createRequest) {
        ContactLink entity = contactLinkMapper.createRequestToEntity(createRequest);
        contactLinkRepository.save(entity);
        return contactLinkMapper.entityToCreateResponse(entity);
    }

    @Override
    public ContactLinkUpdateResponse update(Long id ,ContactLinkUpdateRequest updateRequest) {
        ContactLink entity = contactLinkRepository.findById(id)
                .orElseThrow(() -> new ContactLinkNotFoundException(ErrorMessage.CONTACT_LINK_NOT_FOUND, HttpStatus.NOT_FOUND.name()));
        ContactLink update = contactLinkMapper.updateRequestToEntity(entity, updateRequest);
        contactLinkRepository.save(update);
        return contactLinkMapper.entityToUpdateResponse(update);
    }

    @Override
    public ContactLinkReadResponse getById(Long id) {
        ContactLink entity = contactLinkRepository.findById(id)
                .orElseThrow(() -> new ContactLinkNotFoundException(ErrorMessage.CONTACT_LINK_NOT_FOUND, HttpStatus.NOT_FOUND.name()));
        return contactLinkMapper.entityToReadResponse(entity);
    }

    @Override
    public List<ContactLinkReadResponse> getAll() {
        return contactLinkRepository.findAll()
                .stream().map(contactLinkMapper::entityToReadResponse)
                .toList();
    }

    @Override
    public void delete(Long id) {
        ContactLink entity = contactLinkRepository.findById(id)
                .orElseThrow(() -> new ContactLinkNotFoundException(ErrorMessage.CONTACT_LINK_NOT_FOUND, HttpStatus.NOT_FOUND.name()));
        contactLinkRepository.deleteById(entity.getId());
    }
}
