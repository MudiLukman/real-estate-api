package domain.services;

import domain.entities.Flat;
import domain.entities.House;
import domain.entities.SelfContain;
import domain.entities.Tenant;
import domain.value_objects.Address;
import domain.value_objects.HouseType;

import java.util.Set;
import java.util.UUID;

public class HouseOperation {

    public House createHouse(UUID houseId, HouseType type, Address address, Set<Tenant> tenants) {
        House house;
        if (type.equals(HouseType.FLAT)) {
            house = new Flat(houseId, address);
        } else {
            house = new SelfContain(houseId, address);
        }
        tenants.forEach(house::addTenant);
        return house;
    }

}
