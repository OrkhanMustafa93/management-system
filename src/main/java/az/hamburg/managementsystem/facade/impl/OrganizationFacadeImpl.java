package az.hamburg.managementsystem.facade.impl;


import az.hamburg.managementsystem.facade.OrganizationFacade;
import az.hamburg.managementsystem.model.organization.request.OrganizationCreateDetailRequest;
import az.hamburg.managementsystem.model.organization.response.OrganizationCreateDetailResponse;
import az.hamburg.managementsystem.service.OrganizationService;
import az.hamburg.managementsystem.service.UserService;

public class OrganizationFacadeImpl implements OrganizationFacade {

    private OrganizationService organizationService;
    private UserService userService;

    @Override
    public OrganizationCreateDetailResponse createDetail(OrganizationCreateDetailRequest request, Long userId) {


        return null;

    }
}
