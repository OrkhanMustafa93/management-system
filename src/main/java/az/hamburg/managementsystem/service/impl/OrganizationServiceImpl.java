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
import az.hamburg.managementsystem.repository.UserRepository;
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
    private final UserRepository userRepository;

    @Override
    public OrganizationCreateResponse create(OrganizationCreateRequest createRequest) {

        Organization organization = organizationMapper.createRequestToEntity(createRequest);
        Organization saved = organizationRepository.save(organization);
        return organizationMapper.entityToCreateResponse(saved);

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
    }

    @Override
    public OrganizationUpdateResponse update(Long id, OrganizationUpdateRequest updateRequest) {

        User userReadResponse = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(ErrorMessage.USER_NOT_FOUND, HttpStatus.NOT_FOUND.name()));
        if (userReadResponse.getRoleType().equals(RoleType.ADMIN)) {
            Organization foundedOrganization = organizationRepository
                    .findById(id)
                    .orElseThrow(() -> new OrganizationNotFoundException(ErrorMessage.ORGANIZATION_NOT_FOUND, HttpStatus.NOT_FOUND.name()));
            Organization savedOrganization = organizationMapper.updateRequestToEntity(updateRequest);
            savedOrganization.setId(foundedOrganization.getId());
            organizationRepository.save(savedOrganization);
            return organizationMapper.entityToUpdateResponse(savedOrganization);
        }
        throw new UserUnAuthorizedException(ErrorMessage.USERUNAUTHORIZED, HttpStatus.UNAUTHORIZED.name());

    }

    @Override
    public void delete(Long id, Long userId) {

        User userReadResponse = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(ErrorMessage.USER_NOT_FOUND, HttpStatus.NOT_FOUND.name()));
        if (!userReadResponse.getRoleType().equals(RoleType.ADMIN)) {
            throw new UserUnAuthorizedException(ErrorMessage.USERUNAUTHORIZED, HttpStatus.UNAUTHORIZED.name());
        }
        Organization organization = organizationRepository.findById(id)
                .orElseThrow(() -> new OrganizationNotFoundException(ErrorMessage.ORGANIZATION_NOT_FOUND, HttpStatus.NOT_FOUND.name()));
        organizationRepository.deleteById(organization.getId());
    }

}