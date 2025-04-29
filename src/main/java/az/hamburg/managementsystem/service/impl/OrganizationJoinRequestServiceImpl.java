package az.hamburg.managementsystem.service.impl;

import az.hamburg.managementsystem.domain.OrganizationJoinRequest;
import az.hamburg.managementsystem.exception.error.ErrorMessage;
import az.hamburg.managementsystem.exception.handler.OrganizationJoinRequestNotFoundException;
import az.hamburg.managementsystem.mappers.OrganizationJoinRequestMapper;
import az.hamburg.managementsystem.model.organizationjoinrequest.request.OrganizationJoinRequestCreateRequest;
import az.hamburg.managementsystem.model.organizationjoinrequest.request.OrganizationJoinRequestUpdateRequest;
import az.hamburg.managementsystem.model.organizationjoinrequest.response.OrganizationJoinRequestCreateResponse;
import az.hamburg.managementsystem.model.organizationjoinrequest.response.OrganizationJoinRequestReadResponse;
import az.hamburg.managementsystem.model.organizationjoinrequest.response.OrganizationJoinRequestUpdateResponse;
import az.hamburg.managementsystem.repository.OrganizationJoinRequestRepository;
import az.hamburg.managementsystem.service.OrganizationJoinRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrganizationJoinRequestServiceImpl implements OrganizationJoinRequestService {

    private final OrganizationJoinRequestRepository organizationJoinRequestRepository;
    private final OrganizationJoinRequestMapper organizationJoinRequestMapper;

    @Override
    public OrganizationJoinRequestCreateResponse create(OrganizationJoinRequestCreateRequest createRequest) {

        OrganizationJoinRequest entity = organizationJoinRequestMapper.createRequestToEntity(createRequest);
        organizationJoinRequestRepository.save(entity);
        return organizationJoinRequestMapper.entityToCreateResponse(entity);

    }

    @Override
    public OrganizationJoinRequestReadResponse getId(Long id) {
        OrganizationJoinRequest founded = organizationJoinRequestRepository.findById(id)
                .orElseThrow(() -> new OrganizationJoinRequestNotFoundException(ErrorMessage.ORGANIZATION_JOIN_REQUEST_NOT_FOUND, HttpStatus.NOT_FOUND.name()));

        return organizationJoinRequestMapper.entityToReadResponse(founded);
    }

    @Override
    public List<OrganizationJoinRequestReadResponse> getAll() {
        return organizationJoinRequestRepository.findAll()
                .stream().map(organizationJoinRequestMapper::entityToReadResponse)
                .toList();
    }

    @Override
    public OrganizationJoinRequestUpdateResponse update(Long id, OrganizationJoinRequestUpdateRequest updateRequest) {
        OrganizationJoinRequest entity = organizationJoinRequestRepository
                .findById(id).orElseThrow(() -> new OrganizationJoinRequestNotFoundException(ErrorMessage.ORGANIZATION_JOIN_REQUEST_NOT_FOUND, HttpStatus.NOT_FOUND.name()));
        OrganizationJoinRequest saved = organizationJoinRequestMapper.updateRequestToEntity(entity,updateRequest);
        organizationJoinRequestRepository.save(saved);

        return organizationJoinRequestMapper.entityToUpdateResponse(saved);
    }

    @Override
    public void delete(Long id) {

        OrganizationJoinRequest entity = organizationJoinRequestRepository.findById(id)
                .orElseThrow(() -> new OrganizationJoinRequestNotFoundException(ErrorMessage.ORGANIZATION_JOIN_REQUEST_NOT_FOUND, HttpStatus.NOT_FOUND.name()));
        organizationJoinRequestRepository.deleteById(entity.getId());
    }
}
