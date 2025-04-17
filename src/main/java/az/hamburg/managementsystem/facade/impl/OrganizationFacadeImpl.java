package az.hamburg.managementsystem.facade.impl;


import az.hamburg.managementsystem.domain.Contact;
import az.hamburg.managementsystem.domain.Organization;
import az.hamburg.managementsystem.domain.RoleType;
import az.hamburg.managementsystem.exception.error.ErrorMessage;
import az.hamburg.managementsystem.exception.handler.OrganizationNotFoundException;
import az.hamburg.managementsystem.exception.handler.UserUnAuthorizedException;
import az.hamburg.managementsystem.facade.OrganizationFacade;
import az.hamburg.managementsystem.mappers.ContactMapper;
import az.hamburg.managementsystem.mappers.OrganizationMapper;
import az.hamburg.managementsystem.model.address.response.AddressCreateResponse;
import az.hamburg.managementsystem.model.contact.response.ContactCreateResponse;
import az.hamburg.managementsystem.model.contactlink.response.ContactLinkCreateResponse;
import az.hamburg.managementsystem.model.organization.request.OrganizationCreateDetailRequest;
import az.hamburg.managementsystem.model.organization.request.OrganizationCreateRequest;
import az.hamburg.managementsystem.model.organization.response.OrganizationCreateDetailResponse;
import az.hamburg.managementsystem.model.organization.response.OrganizationCreateResponse;
import az.hamburg.managementsystem.model.organization.response.OrganizationReadDetailResponse;
import az.hamburg.managementsystem.model.user.response.UserReadResponse;
import az.hamburg.managementsystem.repository.ContactRepository;
import az.hamburg.managementsystem.repository.OrganizationRepository;
import az.hamburg.managementsystem.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;


@RequiredArgsConstructor
@Service
@Slf4j
public class OrganizationFacadeImpl implements OrganizationFacade {

    private final OrganizationService organizationService;
    private final UserService userService;
    private final ContactService contactService;
    private final ContactLinkService contactLinkService;
    private final OrganizationMapper organizationMapper;
    private final OrganizationRepository organizationRepository;
    private final AddressService addressService;
    private final ContactMapper contactMapper;
    private final ContactRepository contactRepository;



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
        contactRepository.save(contactEntity);


        ContactCreateResponse contactCreateResponse = contactService.create(request.getContact());
        organizationCreateRequest.setContactId(contactCreateResponse.getId());
        Organization entity = organizationRepository.save(organizationCreateRequest);
        organizationMapper.entityToOrganizationCreateDetailResponse(entity);

        ContactLinkCreateResponse contactLinkCreateResponse = contactLinkService.create(request.getContactLink());

        contactCreateResponse.setId(addressCreateResponse.getId());
        contactCreateResponse.setId(contactLinkCreateResponse.getId());


        return organizationMapper.entityToOrganizationCreateDetailResponse(entity);

        //contactLink yarananda contacrId null dusur...
        //address yarananda 2 defe yaranir yaranir....
        //geriyeqayidan response dataya baxacam
    }

    @Override
    public OrganizationReadDetailResponse getByIdOrganizationDetail(Long id) {
         Organization foundedOrganization = organizationRepository.findById(id)
                .orElseThrow(()-> new OrganizationNotFoundException( ErrorMessage.ORGANIZATION_NOT_FOUND,HttpStatus.NOT_FOUND.name()));

        return organizationMapper.entityToOrganizationReadDetailResponse(foundedOrganization);
    }
}

//getById metodu yaz ama deisiklik olcaq quruma bagli contact melumati,adresi ve.s her bir seyi
//metodun adi getByIdOrganizationDetail(burda yazilcaq)
//organizationCreateDetailResponse kimi bir class yaratcam adi olacaq ReadDetailResponse
//ve icerisni organizationCreateDetailResponse kimi doldurcam ve metodda response kimi qaytarcam


