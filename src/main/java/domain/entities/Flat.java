package domain.entities;

import domain.value_objects.Address;
import domain.value_objects.HouseType;

import java.util.HashSet;
import java.util.UUID;

public class Flat extends House {

    public Flat(UUID houseId, Address address) {
        super(houseId, address, new HashSet<>(), HouseType.FLAT, 8);
    }
}
