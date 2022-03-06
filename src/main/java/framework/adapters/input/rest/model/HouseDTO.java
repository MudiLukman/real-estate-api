package framework.adapters.input.rest.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class HouseDTO {
    private UUID id = UUID.randomUUID();
    private AddressDTO address;
    private HouseTypeDTO houseType;
}
