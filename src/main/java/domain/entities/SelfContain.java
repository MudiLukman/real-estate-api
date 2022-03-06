package domain.entities;

import domain.value_objects.Address;
import domain.value_objects.HouseType;

import java.util.HashSet;
import java.util.UUID;

public class SelfContain extends House {

    public SelfContain(UUID houseId, Address address) {
        super(houseId, address, new HashSet<>(), HouseType.SELF_CON, 2);
    }
}
