package az.hamburg.managementsystem.service.impl;

import az.hamburg.managementsystem.domain.Address;
import az.hamburg.managementsystem.domain.Contact;
import az.hamburg.managementsystem.domain.ContactLink;
import az.hamburg.managementsystem.exception.error.ErrorMessage;
import az.hamburg.managementsystem.exception.handler.ContactNotFoundException;
import az.hamburg.managementsystem.mappers.AddressMapper;
import az.hamburg.managementsystem.mappers.ContactLinkMapper;
import az.hamburg.managementsystem.mappers.ContactMapper;
import az.hamburg.managementsystem.model.address.response.AddressReadResponse;
import az.hamburg.managementsystem.model.contact.request.ContactCreateRequest;
import az.hamburg.managementsystem.model.contact.request.ContactUpdateRequest;
import az.hamburg.managementsystem.model.contact.response.ContactCreateResponse;
import az.hamburg.managementsystem.model.contact.response.ContactReadResponse;
import az.hamburg.managementsystem.model.contact.response.ContactUpdateResponse;
import az.hamburg.managementsystem.model.contactlink.response.ContactLinkReadResponse;
import az.hamburg.managementsystem.model.dto.ContactLinkDTO;
import az.hamburg.managementsystem.repository.ContactLinkRepository;
import az.hamburg.managementsystem.repository.ContactRepository;
import az.hamburg.managementsystem.service.AddressService;
import az.hamburg.managementsystem.service.ContactLinkService;
import az.hamburg.managementsystem.service.ContactService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;
    private final ContactMapper contactMapper;
    private final AddressService addressService;
    private final ContactLinkMapper contactLinkMapper;
    private final ContactLinkRepository contactLinkRepository;

    @Override
    public ContactCreateResponse create(ContactCreateRequest createRequest) {
        Contact contact = contactMapper.createRequestToEntity(createRequest);
        contactRepository.save(contact);

        return  contactMapper.entityToCreateResponse(contact);
    }

    @Override
    public ContactReadResponse getId(Long id) {
        Contact foundedContact = contactRepository.findById(id)
                .orElseThrow(() -> new ContactNotFoundException(ErrorMessage.CONTACT_NOT_FOUND, HttpStatus.NOT_FOUND.name()));

        ContactReadResponse contactReadResponse = contactMapper.entityToReadResponse(foundedContact);

        AddressReadResponse foundedAddress = addressService.getId(foundedContact.getAddressId());
        contactReadResponse.setAddress(foundedAddress);

        List<ContactLink> foundedContactLink = contactLinkRepository.findByContactId(foundedContact.getId());

        List<ContactLinkDTO> contactLinkDto =foundedContactLink.stream()
                .map(contactLinkMapper::entityToContactLinkDto)
                .toList();
        contactReadResponse.setLinks(contactLinkDto);

        return contactReadResponse;

    }

    @Override
    public List<ContactReadResponse> getAll() {
        List<Contact> contactList = contactRepository.findAll();

        return contactList.stream()
                .map(contactMapper::entityToReadResponse)
                .toList();
    }

    @Override
    public ContactUpdateResponse update(Long id, ContactUpdateRequest updateRequest) {
        Contact foundedContact = contactRepository
                .findById(id).orElseThrow(() -> new ContactNotFoundException(ErrorMessage.CONTACT_NOT_FOUND, HttpStatus.NOT_FOUND.name()));
        Contact savedContact = contactMapper.updateRequestToEntity(foundedContact,updateRequest);
        contactRepository.save(savedContact);

        return contactMapper.entityToUpdateResponse(savedContact);
    }

    @Override
    public void delete(Long id) {
        Contact contact = contactRepository.findById(id)
                .orElseThrow(() -> new ContactNotFoundException(ErrorMessage.CONTACT_NOT_FOUND, HttpStatus.NOT_FOUND.name()));
        contactRepository.deleteById(contact.getId());

    }
}
