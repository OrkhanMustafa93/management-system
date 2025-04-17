package az.hamburg.managementsystem.model.address.request;

import az.hamburg.managementsystem.validation.address.AddressField;
import az.hamburg.managementsystem.validation.address.Latitude;
import az.hamburg.managementsystem.validation.address.Longitude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressCreateRequest {

    @AddressField
    private String address;

    @Latitude
    private Double latitude;

    @Longitude
    private Double longitude;

}
