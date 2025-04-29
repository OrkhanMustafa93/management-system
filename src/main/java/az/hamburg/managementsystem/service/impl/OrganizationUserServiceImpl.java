package az.hamburg.managementsystem.service.impl;

import az.hamburg.managementsystem.domain.OrganizationUser;
import az.hamburg.managementsystem.exception.error.ErrorMessage;
import az.hamburg.managementsystem.exception.handler.OrganizationUserNotFoundException;
import az.hamburg.managementsystem.mappers.OrganizationUserMapper;
import az.hamburg.managementsystem.model.organizationuser.request.OrganizationUserCreateRequest;
import az.hamburg.managementsystem.model.organizationuser.request.OrganizationUserUpdateRequest;
import az.hamburg.managementsystem.model.organizationuser.response.OrganizationUserCreateResponse;
import az.hamburg.managementsystem.model.organizationuser.response.OrganizationUserReadResponse;
import az.hamburg.managementsystem.model.organizationuser.response.OrganizationUserUpdateResponse;
import az.hamburg.managementsystem.repository.OrganizationUserRepository;
import az.hamburg.managementsystem.service.OrganizationUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrganizationUserServiceImpl implements OrganizationUserService {
    private final OrganizationUserRepository organizationUserRepository;
    private final OrganizationUserMapper organizationUserMapper;

    @Override
    public OrganizationUserCreateResponse create(OrganizationUserCreateRequest createRequest) {
        OrganizationUser organization = organizationUserMapper.createRequestToEntity(createRequest);
        OrganizationUser saved = organizationUserRepository.save(organization);
        return organizationUserMapper.entityToCreateResponse(saved);
    }

    @Override
    public OrganizationUserReadResponse getId(Long id) {
        OrganizationUser foundedOrganizationUser = organizationUserRepository.findById(id)
                .orElseThrow(() -> new OrganizationUserNotFoundException(ErrorMessage.ORGANIZATION_USER_NOT_FOUND, HttpStatus.NOT_FOUND.name()));

        return organizationUserMapper.entityToReadResponse(foundedOrganizationUser);
    }

    @Override
    public List<OrganizationUserReadResponse> getAll() {
        List<OrganizationUser> organizationUsersList = organizationUserRepository.findAll();
        return organizationUsersList.stream()
                .map(organizationUserMapper::entityToReadResponse)
                .toList();
    }

    @Override
    public OrganizationUserUpdateResponse update(Long id, OrganizationUserUpdateRequest updateRequest) {
        OrganizationUser foundedOrganizationUser = organizationUserRepository
                .findById(id).orElseThrow(() -> new OrganizationUserNotFoundException(ErrorMessage.ORGANIZATION_USER_NOT_FOUND, HttpStatus.NOT_FOUND.name()));
        OrganizationUser savedOrganizationUser = organizationUserMapper.updateRequestToEntity(foundedOrganizationUser,updateRequest);
        organizationUserRepository.save(savedOrganizationUser);

        return organizationUserMapper.entityToUpdateResponse(savedOrganizationUser);
    }

    @Override
    public void delete(Long id) {
        OrganizationUser organizationUser = organizationUserRepository.findById(id)
                .orElseThrow(() -> new OrganizationUserNotFoundException(ErrorMessage.ORGANIZATION_USER_NOT_FOUND, HttpStatus.NOT_FOUND.name()));
        organizationUserRepository.deleteById(organizationUser.getId());
    }
}
