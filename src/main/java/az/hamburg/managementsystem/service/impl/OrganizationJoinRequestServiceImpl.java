package az.hamburg.managementsystem.service.impl;

import az.hamburg.managementsystem.domain.OrganizationJoinRequest;
import az.hamburg.managementsystem.domain.OrganizationUser;
import az.hamburg.managementsystem.domain.RequestStatus;
import az.hamburg.managementsystem.domain.RoleType;
import az.hamburg.managementsystem.exception.error.ErrorMessage;
import az.hamburg.managementsystem.exception.handler.OrganizationJoinRequestNotFoundException;
import az.hamburg.managementsystem.exception.handler.OrganizationNotFoundException;
import az.hamburg.managementsystem.exception.handler.UserUnAuthorizedException;
import az.hamburg.managementsystem.mappers.OrganizationJoinRequestMapper;
import az.hamburg.managementsystem.model.dto.OrganizationJoinRequestUpdateStatus;
import az.hamburg.managementsystem.model.organization.response.OrganizationReadResponse;
import az.hamburg.managementsystem.model.organizationjoinrequest.request.OrganizationJoinRequestCreateRequest;
import az.hamburg.managementsystem.model.organizationjoinrequest.request.OrganizationJoinRequestUpdateRequest;
import az.hamburg.managementsystem.model.organizationjoinrequest.response.OrganizationJoinRequestCreateResponse;
import az.hamburg.managementsystem.model.organizationjoinrequest.response.OrganizationJoinRequestReadResponse;
import az.hamburg.managementsystem.model.organizationjoinrequest.response.OrganizationJoinRequestUpdateResponse;
import az.hamburg.managementsystem.model.user.response.UserReadResponse;
import az.hamburg.managementsystem.repository.OrganizationJoinRequestRepository;
import az.hamburg.managementsystem.repository.OrganizationUserRepository;
import az.hamburg.managementsystem.service.OrganizationJoinRequestService;
import az.hamburg.managementsystem.service.OrganizationService;
import az.hamburg.managementsystem.service.OrganizationUserService;
import az.hamburg.managementsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrganizationJoinRequestServiceImpl implements OrganizationJoinRequestService {

    private final OrganizationJoinRequestRepository organizationJoinRequestRepository;
    private final OrganizationJoinRequestMapper organizationJoinRequestMapper;
    private final OrganizationService organizationService;
    private final UserService userService;
    private final OrganizationUserRepository organizationUserRepository;

    @Override
    public OrganizationJoinRequestCreateResponse create(OrganizationJoinRequestCreateRequest createRequest) {
        organizationService.getId(createRequest.getOrganizationId());
        userService.getId(createRequest.getUserId());

        OrganizationJoinRequest entity = organizationJoinRequestMapper.createRequestToEntity(createRequest);
        entity.setStatus(RequestStatus.PENDING);
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
    public OrganizationJoinRequestUpdateResponse update(OrganizationJoinRequestUpdateRequest updateRequest) {

        UserReadResponse user = userService.getId(updateRequest.getId());
        if (!user.getRoleType().equals(RoleType.ADMIN)) {
            throw new UserUnAuthorizedException(ErrorMessage.USERUNAUTHORIZED, HttpStatus.UNAUTHORIZED.name());
        }
        OrganizationJoinRequest entity = organizationJoinRequestRepository.findByUserIdAndOrganizationId(updateRequest.getUserId(), updateRequest.getOrganizationId())
                        .orElseThrow(()-> new OrganizationJoinRequestNotFoundException(ErrorMessage.ORGANIZATION_JOIN_REQUEST_NOT_FOUND, HttpStatus.NOT_FOUND.name()));

        OrganizationUser organizationUser = new OrganizationUser();
        organizationUser.setUserId(updateRequest.getUserId());
        organizationUser.setOrganizationId(updateRequest.getOrganizationId());

        entity.setStatus(updateRequest.getStatus());

        if (updateRequest.getStatus().equals(RequestStatus.APPROVED)){
            organizationJoinRequestRepository.save(entity);
            organizationUserRepository.save(organizationUser);
        }
             return organizationJoinRequestMapper.entityToUpdateResponse(entity);

    }

    @Override
    public void delete(Long id) {

        OrganizationJoinRequest entity = organizationJoinRequestRepository.findById(id)
                .orElseThrow(() -> new OrganizationJoinRequestNotFoundException(ErrorMessage.ORGANIZATION_JOIN_REQUEST_NOT_FOUND, HttpStatus.NOT_FOUND.name()));
        organizationJoinRequestRepository.deleteById(entity.getId());
    }

    @Override
    public List<OrganizationJoinRequestReadResponse> getAllStatusPending() {
        return organizationJoinRequestRepository.getAllOrganizationJoinRequestsByStatus(RequestStatus.PENDING)
                .stream()
                .map(organizationJoinRequestMapper::entityToReadResponse)
                .toList();

    }

    @Override
    public List<OrganizationJoinRequestReadResponse> getAllByOrganizationId(Long organizationId) {
        return  organizationJoinRequestRepository.getAllOrganizationJoinRequestByOrganizationId(organizationId)
                .stream()
                .map(organizationJoinRequestMapper::entityToReadResponse)
                .toList();
    }

    @Override
    public List<OrganizationJoinRequestReadResponse> getAllByOrganizationIdAndStatus(Long organizationId) {

        return  organizationJoinRequestRepository.findOrganizationRequestByOrganizationId(organizationId).stream()
                .map(organizationJoinRequestMapper::entityToReadResponse)
                .toList();
    }
}