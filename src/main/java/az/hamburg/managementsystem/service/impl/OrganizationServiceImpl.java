package az.hamburg.managementsystem.service.impl;

import az.hamburg.managementsystem.exception.error.ErrorMessage;
import az.hamburg.managementsystem.exception.handler.OrganizationNotFoundException;
import az.hamburg.managementsystem.mappers.OrganizationMapper;
import az.hamburg.managementsystem.model.request.OrganizationCreateRequest;
import az.hamburg.managementsystem.model.request.OrganizationUpdateRequest;
import az.hamburg.managementsystem.model.response.OrganizationCreateResponse;
import az.hamburg.managementsystem.model.response.OrganizationReadResponse;
import az.hamburg.managementsystem.model.response.OrganizationUpdateResponse;
import az.hamburg.managementsystem.repository.OrganizationRepository;
import az.hamburg.managementsystem.service.OrganizationService;
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

    @Override
    public OrganizationCreateResponse create(OrganizationCreateRequest createRequest) {
        Organization organization = organizationMapper.createRequestToEntity(createRequest);
        Organization saved = organizationRepository.save(organization);
        return  organizationMapper.entityToCreateResponse(organization);
    }

    @Override
    public OrganizationReadResponse getId(Long id) {
        Organization foundedOrganization = organizationRepository.findById(id)
                .orElseThrow(() -> new OrganizationNotFoundException(ErrorMessage.Organization_NOT_FOUND, HttpStatus.NOT_FOUND.name()));

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
                Organization foundedOrganization = organizationRepository
                .findById(id)
                        .orElseThrow(() -> new OrganizationNotFoundException(ErrorMessage.Organization_NOT_FOUND, HttpStatus.NOT_FOUND.name()));
        Organization savedOrganization = organizationMapper.updateRequestToEntity(updateRequest);
        savedOrganization.setId(foundedOrganization.getId());
        organizationRepository.save(savedOrganization);

        return organizationMapper.entityToUpdateResponse(savedOrganization);
    }

    @Override
    public void delete(Long id) {
                Organization organization = organizationRepository.findById(id)
                .orElseThrow(() -> new OrganizationNotFoundException(ErrorMessage.Organization_NOT_FOUND, HttpStatus.NOT_FOUND.name()));
        organizationRepository.deleteById(organization.getId());
    }
}
