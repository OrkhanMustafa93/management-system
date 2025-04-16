package az.hamburg.managementsystem.service;

import az.hamburg.managementsystem.model.address.request.AddressCreateRequest;
import az.hamburg.managementsystem.model.address.request.AddressUpdateRequest;
import az.hamburg.managementsystem.model.address.response.AddressCreateResponse;
import az.hamburg.managementsystem.model.address.response.AddressReadResponse;
import az.hamburg.managementsystem.model.address.response.AddressUpdateResponse;

import java.util.List;

public interface AddressService {

    AddressCreateResponse create(AddressCreateRequest addressCreateRequest);

    AddressUpdateResponse update(Long id, AddressUpdateRequest addressUpdateRequest);

    AddressReadResponse getId(Long id);

    List<AddressReadResponse> getAll();

    void delete(Long id);

}
