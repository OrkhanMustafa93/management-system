package az.hamburg.managementsystem.service.impl;

import az.hamburg.managementsystem.common.SelectIds;
import az.hamburg.managementsystem.domain.Address;
import az.hamburg.managementsystem.domain.Role;
import az.hamburg.managementsystem.domain.RoleType;
import az.hamburg.managementsystem.domain.User;
import az.hamburg.managementsystem.exception.error.ErrorMessage;
import az.hamburg.managementsystem.exception.handler.AddressNotFoundException;
import az.hamburg.managementsystem.exception.handler.UserNotFoundException;
import az.hamburg.managementsystem.exception.handler.UserUnAuthorizedException;
import az.hamburg.managementsystem.mappers.AddressMapper;
import az.hamburg.managementsystem.mappers.UserMapper;
import az.hamburg.managementsystem.model.address.request.AddressCreateRequest;
import az.hamburg.managementsystem.model.address.request.AddressUpdateRequest;
import az.hamburg.managementsystem.model.address.response.AddressCreateResponse;
import az.hamburg.managementsystem.model.address.response.AddressReadResponse;
import az.hamburg.managementsystem.model.address.response.AddressUpdateResponse;
import az.hamburg.managementsystem.model.user.request.UserUpdateRequest;
import az.hamburg.managementsystem.model.user.response.UserReadResponse;
import az.hamburg.managementsystem.repository.AddressRepository;
import az.hamburg.managementsystem.repository.UserRepository;
import az.hamburg.managementsystem.service.AddressService;
import az.hamburg.managementsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;


    @Override
    public AddressCreateResponse create(AddressCreateRequest addressCreateRequest) {
        Address address = addressMapper.createRequestToEntity(addressCreateRequest);
        Address saved = addressRepository.save(address);
        return addressMapper.entityToCreateResponse(saved);
    }

    @Override
    public AddressUpdateResponse update(Long id, AddressUpdateRequest addressUpdateRequest) {

        Address foundedAddress = addressRepository
                .findById(id).orElseThrow(() -> new UserNotFoundException(ErrorMessage.ADDRESS_NOT_FOUND, HttpStatus.NOT_FOUND.name()));
        Address savedAddress = addressMapper.updateRequestToEntity(addressUpdateRequest);
        savedAddress.setId(foundedAddress.getId());
        addressRepository.save(savedAddress);

        return addressMapper.entityToUpdateResponse(savedAddress);

    }

    @Override
    public AddressReadResponse getId(Long id) {
        Address foundedAddress = addressRepository.findById(id)
                .orElseThrow(() -> new AddressNotFoundException(ErrorMessage.ADDRESS_NOT_FOUND, HttpStatus.NOT_FOUND.name()));

        return addressMapper.entityToReadResponse(foundedAddress);
    }

    @Override
    public List<AddressReadResponse> getAll() {
        List<Address> addressList = addressRepository.findAll();

        return addressList.stream()
                .map(addressMapper::entityToReadResponse)
                .toList();
    }

    @Override
    public void delete(SelectIds selectIds) {
        List<Address> addresses = addressRepository.findAllById(selectIds.getIds());

        List<Long> addressIds = new ArrayList<>();

        for (Address address : addresses) {
            addressIds.add(address.getId());
        }

        selectIds.getIds().removeAll(addressIds);
        log.info("SelectIds " + selectIds.getIds() + " selectIds");

        if (addresses.size() != selectIds.getIds().size()) {
            throw new AddressNotFoundException(ErrorMessage.ADDRESS_NOT_FOUND, HttpStatus.NOT_FOUND.name());
        }

        addressRepository.deleteAllById(selectIds.getIds());
    }

}
