package az.hamburg.managementsystem.controller;

import az.hamburg.managementsystem.common.SelectIds;
import az.hamburg.managementsystem.model.address.request.AddressCreateRequest;
import az.hamburg.managementsystem.model.address.request.AddressUpdateRequest;
import az.hamburg.managementsystem.model.address.response.AddressCreateResponse;
import az.hamburg.managementsystem.model.address.response.AddressReadResponse;
import az.hamburg.managementsystem.model.address.response.AddressUpdateResponse;
import az.hamburg.managementsystem.service.AddressService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/addresses")
@RequiredArgsConstructor
@Tag(name = "Address Controller API", description = "Managing Address Apis")
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class AddressController {

    private final AddressService addressService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AddressCreateResponse create(@Valid @RequestBody AddressCreateRequest createRequest) {
        return addressService.create(createRequest);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AddressReadResponse getId(@PathVariable Long id) {
        return addressService.getId(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AddressReadResponse> getAll() {
        return addressService.getAll();
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@RequestBody SelectIds request) {
        addressService.delete(request);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AddressUpdateResponse update(@PathVariable Long id, @Valid @RequestBody AddressUpdateRequest updateRequest) {
        return addressService.update(id, updateRequest);
    }
}
