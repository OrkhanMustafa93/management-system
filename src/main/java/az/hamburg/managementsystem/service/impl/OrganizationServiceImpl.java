package az.hamburg.managementsystem.service.impl;

import az.hamburg.managementsystem.domain.Organization;
import az.hamburg.managementsystem.domain.RoleType;
import az.hamburg.managementsystem.domain.User;
import az.hamburg.managementsystem.exception.error.ErrorMessage;
import az.hamburg.managementsystem.exception.handler.OrganizationNotFoundException;
import az.hamburg.managementsystem.exception.handler.UserNotFoundException;
import az.hamburg.managementsystem.exception.handler.UserUnAuthorizedException;
import az.hamburg.managementsystem.mappers.OrganizationMapper;
import az.hamburg.managementsystem.model.organization.request.OrganizationCreateRequest;
import az.hamburg.managementsystem.model.organization.request.OrganizationUpdateRequest;
import az.hamburg.managementsystem.model.organization.response.OrganizationCreateResponse;
import az.hamburg.managementsystem.model.organization.response.OrganizationReadResponse;
import az.hamburg.managementsystem.model.organization.response.OrganizationUpdateResponse;
import az.hamburg.managementsystem.model.user.response.UserReadResponse;
import az.hamburg.managementsystem.repository.OrganizationRepository;
import az.hamburg.managementsystem.service.OrganizationService;
import az.hamburg.managementsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository organizationRepository;
    private final OrganizationMapper organizationMapper;
    private final UserService userService;

    @Override
    public OrganizationCreateResponse create(OrganizationCreateRequest createRequest, Long userId) {
        UserReadResponse userReadResponse = userService.getId(userId);
        if (userReadResponse.getRoleType().equals(RoleType.ADMIN)){
            Organization organization = organizationMapper.createRequestToEntity(createRequest);
//            Organization saved = organizationRepository.save(organization);
            return  organizationMapper.entityToCreateResponse(organization);
        }
        throw new UserUnAuthorizedException(ErrorMessage.USERUNAUTHORIZED, HttpStatus.UNAUTHORIZED.name());
//        if (userId != null) {
//            Organization organization = organizationMapper.createRequestToEntity(createRequest);
//            Organization saved = organizationRepository.save(organization);
//            return  organizationMapper.entityToCreateResponse(organization);
//        }
//        throw new UserNotFoundException(ErrorMessage.USER_NOT_FOUND, HttpStatus.NOT_FOUND.name());
//        todo: createdby kim terefinden yaradildigi (set olunmalidi)
//        todo: istifadeci organization daxil etmelidi, var olub olmadigi da yoxlanilmalidi (id si ile)
//        todo: creatdeki mentiqi update ve delete de tetbiq etmek
    }


    @Override
    public OrganizationReadResponse getId(Long id) {
        Organization foundedOrganization = organizationRepository.findById(id)
                .orElseThrow(() -> new OrganizationNotFoundException(ErrorMessage.ORGANIZATION_NOT_FOUND, HttpStatus.NOT_FOUND.name()));

        return organizationMapper.entityToReadResponse(foundedOrganization);
    }

    @Override
    public List<OrganizationReadResponse> getAll() {
        List<Organization> organizationList = organizationRepository.findAll();
        return organizationList.stream().map(organizationMapper::entityToReadResponse).collect(Collectors.toList());
//        return organizationMapper.listEntityToListReadResponse(organizationList);
    }

    @Override
    public OrganizationUpdateResponse update(Long id, OrganizationUpdateRequest updateRequest) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public OrganizationUpdateResponse update(Long id, OrganizationUpdateRequest updateRequest, User user) {
        if (user != null){
            Organization foundedOrganization = organizationRepository
                    .findById(id)
                    .orElseThrow(() -> new OrganizationNotFoundException(ErrorMessage.ORGANIZATION_NOT_FOUND, HttpStatus.NOT_FOUND.name()));
            Organization savedOrganization = organizationMapper.updateRequestToEntity(updateRequest);
            savedOrganization.setId(foundedOrganization.getId());
            organizationRepository.save(savedOrganization);

            return organizationMapper.entityToUpdateResponse(savedOrganization);
        }
        throw new UserNotFoundException(ErrorMessage.USER_NOT_FOUND, HttpStatus.NOT_FOUND.name());

//                Organization foundedOrganization = organizationRepository
//                .findById(id)
//                        .orElseThrow(() -> new OrganizationNotFoundException(ErrorMessage.ORGANIZATION_NOT_FOUND, HttpStatus.NOT_FOUND.name()));
//        Organization savedOrganization = organizationMapper.updateRequestToEntity(updateRequest);
//        savedOrganization.setId(foundedOrganization.getId());
//        organizationRepository.save(savedOrganization);

//        return organizationMapper.entityToUpdateResponse(savedOrganization);
    }

    @Override
    public void delete(Long id, User user) {
        if (user != null){
            Organization organization = organizationRepository.findById(id)
                    .orElseThrow(() -> new OrganizationNotFoundException(ErrorMessage.ORGANIZATION_NOT_FOUND, HttpStatus.NOT_FOUND.name()));
            organizationRepository.deleteById(organization.getId());
        }
        throw new UserNotFoundException(ErrorMessage.USER_NOT_FOUND, HttpStatus.NOT_FOUND.name());


//                Organization organization = organizationRepository.findById(id)
//                .orElseThrow(() -> new OrganizationNotFoundException(ErrorMessage.ORGANIZATION_NOT_FOUND, HttpStatus.NOT_FOUND.name()));
//        organizationRepository.deleteById(organization.getId());
    }
}