package az.hamburg.managementsystem.facade.impl;


import az.hamburg.managementsystem.SelectIds;
import az.hamburg.managementsystem.domain.Contact;
import az.hamburg.managementsystem.domain.ContactLink;
import az.hamburg.managementsystem.domain.Organization;
import az.hamburg.managementsystem.domain.RoleType;
import az.hamburg.managementsystem.exception.error.ErrorMessage;
import az.hamburg.managementsystem.exception.handler.ContactNotFoundException;
import az.hamburg.managementsystem.exception.handler.OrganizationNotFoundException;
import az.hamburg.managementsystem.exception.handler.UserUnAuthorizedException;
import az.hamburg.managementsystem.facade.OrganizationFacade;
import az.hamburg.managementsystem.mappers.ContactLinkMapper;
import az.hamburg.managementsystem.mappers.ContactMapper;
import az.hamburg.managementsystem.mappers.OrganizationMapper;
import az.hamburg.managementsystem.model.address.response.AddressCreateResponse;
import az.hamburg.managementsystem.model.address.response.AddressReadResponse;
import az.hamburg.managementsystem.model.address.response.AddressUpdateResponse;
import az.hamburg.managementsystem.model.contact.response.ContactCreateResponse;
import az.hamburg.managementsystem.model.contact.response.ContactReadResponse;
import az.hamburg.managementsystem.model.contact.response.ContactUpdateResponse;
import az.hamburg.managementsystem.model.contactlink.request.ContactLinkCreateRequest;
import az.hamburg.managementsystem.model.contactlink.request.ContactLinkUpdateRequest;
import az.hamburg.managementsystem.model.contactlink.response.ContactLinkCreateResponse;
import az.hamburg.managementsystem.model.contactlink.response.ContactLinkReadResponse;
import az.hamburg.managementsystem.model.contactlink.response.ContactLinkUpdateResponse;
import az.hamburg.managementsystem.model.dto.ContactLinkDTO;
import az.hamburg.managementsystem.model.dto.OrganizationStatusReadResponse;
import az.hamburg.managementsystem.model.organization.request.OrganizationCreateDetailRequest;
import az.hamburg.managementsystem.model.organization.request.OrganizationUpdateDetailRequest;
import az.hamburg.managementsystem.model.organization.response.OrganizationCreateDetailResponse;
import az.hamburg.managementsystem.model.organization.response.OrganizationReadDetailResponse;
import az.hamburg.managementsystem.model.organization.response.OrganizationReadResponse;
import az.hamburg.managementsystem.model.organization.response.OrganizationUpdateDetailResponse;
import az.hamburg.managementsystem.model.user.response.UserReadResponse;
import az.hamburg.managementsystem.repository.ContactLinkRepository;
import az.hamburg.managementsystem.repository.ContactRepository;
import az.hamburg.managementsystem.repository.OrganizationRepository;
import az.hamburg.managementsystem.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Service
@Slf4j
public class OrganizationFacadeImpl implements OrganizationFacade {

    private final UserService userService;
    private final ContactLinkService contactLinkService;
    private final OrganizationMapper organizationMapper;
    private final OrganizationRepository organizationRepository;
    private final AddressService addressService;
    private final ContactMapper contactMapper;
    private final ContactRepository contactRepository;
    private final ContactService contactService;
    private final OrganizationService organizationService;


    @Override
    public OrganizationCreateDetailResponse createDetail(OrganizationCreateDetailRequest request, Long userId) {
        UserReadResponse foundUser = userService.getId(userId);
        if (!foundUser.getRoleType().equals(RoleType.ADMIN)) {
            throw new UserUnAuthorizedException(ErrorMessage.USERUNAUTHORIZED, HttpStatus.UNAUTHORIZED.name());
        }
        Organization organizationCreateRequest = organizationMapper.organizationCreateDetailRequestToEntity(request);
        log.info("Created Address : {}", request.getAddress());
        AddressCreateResponse addressCreateResponse = addressService.create(request.getAddress());

        Contact contactEntity = contactMapper.createRequestToEntity(request.getContact());
        contactEntity.setAddressId(addressCreateResponse.getId());
        Contact contactSaved = contactRepository.save(contactEntity);
        ContactCreateResponse contactCreateResponse = contactMapper.entityToCreateResponse(contactSaved);

        organizationCreateRequest.setContactId(contactSaved.getId());
        Organization entity = organizationRepository.save(organizationCreateRequest);
        organizationMapper.entityToOrganizationCreateDetailResponse(entity);

        List<ContactLinkCreateResponse> contactLinkCreateResponseList = new ArrayList<>();
        for (ContactLinkCreateRequest contactLinkCreateRequest : request.getContactLinks()) {
            contactLinkCreateRequest.setContactId(contactSaved.getId());
            ContactLinkCreateResponse contactLinkCreateResponse = contactLinkService.create(contactLinkCreateRequest);
            contactSaved.setContactLinkId(contactLinkCreateResponse.getId());
            contactLinkCreateResponseList.add(contactLinkCreateResponse);

        }
        return OrganizationCreateDetailResponse
                .builder()
                .id(organizationCreateRequest.getId())
                .name(organizationCreateRequest.getName())
                .status(organizationCreateRequest.getStatus())
                .address(addressCreateResponse)
                .contact(contactCreateResponse)
                .contactLinks(contactLinkCreateResponseList)
                .createdBy(organizationCreateRequest.getCreatedBy())
                .build();

    }

    @Override
    public OrganizationReadDetailResponse getByIdOrganizationDetail(Long id) {
        Organization foundedOrganization = organizationRepository.findById(id)
                .orElseThrow(() -> new OrganizationNotFoundException(ErrorMessage.ORGANIZATION_NOT_FOUND, HttpStatus.NOT_FOUND.name()));

        ContactReadResponse contactReadResponse = contactService.getId(foundedOrganization.getContactId());

        return OrganizationReadDetailResponse.builder()
                .id(foundedOrganization.getId())
                .name(foundedOrganization.getName())
                .status(foundedOrganization.getStatus())
                .contact(contactReadResponse)
                .build();


    }

    @Override
    public OrganizationUpdateDetailResponse updateDetail(Long userId,Long organizationId, OrganizationUpdateDetailRequest updateDetailRequest) {
        UserReadResponse foundUser = userService.getId(userId);
        if (!foundUser.getRoleType().equals(RoleType.MODERATOR)) {
            throw new UserUnAuthorizedException(ErrorMessage.USERUNAUTHORIZED, HttpStatus.UNAUTHORIZED.name());
        }
        Organization foundOrganization = organizationRepository.findById(organizationId)
                .orElseThrow(() -> new OrganizationNotFoundException(ErrorMessage.ORGANIZATION_NOT_FOUND, HttpStatus.NOT_FOUND.name()));


        Organization organization = organizationMapper.updateDetailRequestToEntity(updateDetailRequest);
        organization.setId(foundOrganization.getId());

        AddressUpdateResponse addressUpdateResponse = addressService.update(userId, updateDetailRequest.getAddress());

        Contact contact = contactRepository.findById(userId)
                .orElseThrow(() -> new ContactNotFoundException(ErrorMessage.CONTACT_NOT_FOUND, HttpStatus.NOT_FOUND.name()));

        Contact contactEntity = contactMapper.updateRequestToEntity(contact, updateDetailRequest.getContact());
        contactEntity.setAddressId(addressUpdateResponse.getId());
        Contact entitySave = contactRepository.save(contactEntity);
        ContactUpdateResponse contactUpdateResponse = contactMapper.entityToUpdateResponse(entitySave);

        organization.setContactId(entitySave.getId());
        Organization entity = organizationRepository.save(organization);
        organizationMapper.entityToUpdateDetailResponse(entity);

        List<ContactLinkUpdateResponse> contactLinkUpdateResponseList = new ArrayList<>();
        for (ContactLinkUpdateRequest contactLinkUpdateRequest : updateDetailRequest.getContactLinks()) {
            contactLinkUpdateRequest.setContactId(entitySave.getId());
            ContactLinkUpdateResponse contactLinkUpdateResponse = contactLinkService.update(userId, contactLinkUpdateRequest);
            entitySave.setContactLinkId(contactLinkUpdateResponse.getId());
            contactLinkUpdateResponseList.add(contactLinkUpdateResponse);
        }
        return OrganizationUpdateDetailResponse
                .builder()
                .contactId(organization.getContactId())
                .name(organization.getName())
                .status(organization.getStatus())
                .address(addressUpdateResponse)
                .contact(contactUpdateResponse)
                .contactLinks(contactLinkUpdateResponseList)
                .modifiedBy(organization.getModifiedBy())
                .build();
    }

    @Override
    public void deleteOrganizations(Long userid, SelectIds selectIds) {
        UserReadResponse userReadResponse = userService.getId(userid);

        if (!userReadResponse.getRoleType().equals(RoleType.ADMIN)) {
            throw new UserUnAuthorizedException(ErrorMessage.USERUNAUTHORIZED, HttpStatus.UNAUTHORIZED.name());
        }

        List<Organization> organizations = organizationRepository.findAllById(selectIds.getIds());

        if (organizations.isEmpty()) {
            throw new OrganizationNotFoundException(ErrorMessage.ORGANIZATION_NOT_FOUND, HttpStatus.NOT_FOUND.name());
        }

        List<Long> organizationIds = new ArrayList<>();
        for (Organization organization : organizations) {
            organizationIds.add(organization.getId());
        }
        selectIds.getIds().removeAll(organizationIds);

        //yuxaridaki kodu streamdan yaz..(filter ve sonra liste at)

    }

    @Override
    public OrganizationStatusReadResponse updateStatus(Long userId, Long organizationId, boolean status) {

        UserReadResponse foundedUser = userService.getId(userId);

        Organization foundedOrganization = organizationRepository.findById(organizationId)
                .orElseThrow(()->new OrganizationNotFoundException(ErrorMessage.ORGANIZATION_NOT_FOUND, HttpStatus.NOT_FOUND.name()));

        if (!foundedUser.getRoleType().equals(RoleType.MODERATOR)) {
            throw new UserUnAuthorizedException(ErrorMessage.USERUNAUTHORIZED, HttpStatus.UNAUTHORIZED.name());
        }

        foundedOrganization.setStatus(status);
        organizationRepository.save(foundedOrganization);

        return organizationMapper.entityToOrganizationStatusReadResponse(foundedOrganization);

    }

    @Override
    public List<OrganizationReadDetailResponse> getAllOrganizationsStatusTrue() {

        List<Organization> allOrganizationsByStatus = organizationRepository.getAllOrganizationsByStatus(Boolean.TRUE);
        return allOrganizationsByStatus.stream()
                .map(organizationMapper::entityToOrganizationReadDetailResponse)
                .toList();

    }

    @Override
    public List<OrganizationReadDetailResponse> getAll() {
        List<OrganizationReadResponse> all = organizationService.getAll();

        List<OrganizationReadDetailResponse> detailResponses = all.stream()
                .map(organizationMapper::readResponseToReadDetail)
                .toList();

        List<OrganizationReadDetailResponse> resultList = new ArrayList<>();
        for (OrganizationReadDetailResponse detail: detailResponses){
            OrganizationReadDetailResponse resultDetail=getByIdOrganizationDetail(detail.getId());
            resultList.add(resultDetail);
        }
        return resultList;
    }
}




//statusu update etmek  ucun ayri method...
//        if (!foundUser.getRoleType().equals(RoleType.MODERATOR)) {
//          organization.setStatus(foundOrganization.getStatus());
//   }


//qurumlarin statusu true olanlari istifadeciler gorsun..
//organizationda olan getAll metodunu istifade etcem istifadeci ucun
//bu serti tetbiq etmeliyem-->Select * from organization where status = TRUE;
//organizationRepository-deki metoddan istifade etcem..


//update metodunun api-sini duzeltcem

//multiDeletede userId esasen roleType yoxlucam UserReadResponsa gore yox
//id gore useri tapcam..

