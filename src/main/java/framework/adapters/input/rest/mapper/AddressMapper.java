package framework.adapters.input.rest.mapper;

import domain.value_objects.Address;
import framework.adapters.input.rest.model.AddressDTO;

public class AddressMapper {

    public static Address toAddress(AddressDTO addressDTO) {
        return new Address(addressDTO.getNumber());
    }

    public static AddressDTO toAddressDTO(Address address) {
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setNumber(address.getNumber());
        return addressDTO;
    }
}
