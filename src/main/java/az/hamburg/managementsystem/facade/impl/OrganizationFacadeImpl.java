package az.hamburg.managementsystem.facade.impl;


import az.hamburg.managementsystem.domain.Organization;
import az.hamburg.managementsystem.domain.RoleType;
import az.hamburg.managementsystem.exception.error.ErrorMessage;
import az.hamburg.managementsystem.exception.handler.UserUnAuthorizedException;
import az.hamburg.managementsystem.facade.OrganizationFacade;
import az.hamburg.managementsystem.mappers.OrganizationMapper;
import az.hamburg.managementsystem.model.contact.response.ContactCreateResponse;
import az.hamburg.managementsystem.model.organization.request.OrganizationCreateDetailRequest;
import az.hamburg.managementsystem.model.organization.request.OrganizationCreateRequest;
import az.hamburg.managementsystem.model.organization.response.OrganizationCreateDetailResponse;
import az.hamburg.managementsystem.model.organization.response.OrganizationCreateResponse;
import az.hamburg.managementsystem.model.user.response.UserReadResponse;
import az.hamburg.managementsystem.repository.OrganizationRepository;
import az.hamburg.managementsystem.service.ContactLinkService;
import az.hamburg.managementsystem.service.ContactService;
import az.hamburg.managementsystem.service.OrganizationService;
import az.hamburg.managementsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.http.HttpStatus;


@RequiredArgsConstructor
public class OrganizationFacadeImpl implements OrganizationFacade {

    private final OrganizationService organizationService;
    private final UserService userService;
    private final ContactService contactService;
    private final ContactLinkService contactLinkService;
    private final OrganizationMapper organizationMapper;
    private final OrganizationRepository  organizationRepository;




    @Override
    public OrganizationCreateDetailResponse createDetail(OrganizationCreateDetailRequest request, Long userId) {
        UserReadResponse foundUser = userService.getId(userId);
        if (!foundUser.getRoleType().equals(RoleType.ADMIN)){
            throw new UserUnAuthorizedException(ErrorMessage.USERUNAUTHORIZED, HttpStatus.UNAUTHORIZED.name());
        }
        OrganizationCreateRequest organizationCreateRequest = organizationMapper.detailToCreateModel(request);
        OrganizationCreateResponse organizationCreateResponse = organizationService.create(organizationCreateRequest);


        Organization requestToEntity = organizationMapper.createRequestToEntity(organizationCreateRequest);
        requestToEntity.setContactId(organizationCreateResponse.getId());
        organizationRepository.save(requestToEntity);



        ContactCreateResponse contactCreateResponse = contactService.create(request.getContact());

        //contactLinkService.create();



        return null;
        //ilk olaraq user yoxlamaq lazimdir olub olmadiqini
        //2:requestden gelen contact bos deilse data bazaya save oluncaq,sonra digerleride
        //save oluncaq..(ama evvelce bos olub olmadqini yoxlamaq lazimdir !)
        // meselene:if(request.getContact() != null){}
        //contactService ve contactLinkService yoxlamaq lazim deil cunki ilk olaraq burada yaranacaq.

    }
}
