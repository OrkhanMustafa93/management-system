package az.hamburg.managementsystem.mappers;

import az.hamburg.managementsystem.domain.Address;
import az.hamburg.managementsystem.model.address.request.AddressCreateRequest;
import az.hamburg.managementsystem.model.address.request.AddressUpdateRequest;
import az.hamburg.managementsystem.model.address.response.AddressCreateResponse;
import az.hamburg.managementsystem.model.address.response.AddressReadResponse;
import az.hamburg.managementsystem.model.address.response.AddressUpdateResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface AddressMapper {

    Address createRequestToEntity(AddressCreateRequest addressCreateRequest);

    Address updateRequestToEntity(AddressUpdateRequest addressUpdateRequest);

    AddressCreateResponse entityToCreateResponse(Address address);

    AddressUpdateResponse entityToUpdateResponse(Address address);

    AddressReadResponse entityToReadResponse(Address address);

}
