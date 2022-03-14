package application.ports.input;

import application.ports.output.HouseManagementOutputPort;
import application.use_cases.HouseManagementUseCase;
import domain.entities.Flat;
import domain.entities.House;
import domain.entities.SelfContain;
import domain.entities.Tenant;
import domain.value_objects.Address;
import domain.value_objects.HouseType;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.UUID;

@ApplicationScoped
public class HouseManagementInputPort implements HouseManagementUseCase {

    @Inject HouseManagementOutputPort houseManagementOutputPort;

    @Override
    public House createHouse(UUID houseId, Address address, HouseType type) {
        House house;
        if (type.equals(HouseType.FLAT)) {
            house = new Flat(houseId, address);
        } else {
            house = new SelfContain(houseId, address);
        }
        return house;
    }

    @Override
    public House retrieveHouse(UUID houseId) {
        return houseManagementOutputPort.retrieveHouse(houseId);
    }

    @Override
    public House persistHouse(House house) {
        houseManagementOutputPort.persistHouse(house);
        return houseManagementOutputPort.retrieveHouse(house.getHouseId());
    }

    @Override
    public House addTenantToHouse(House house, Tenant tenant) {
        house.addTenant(tenant);
        houseManagementOutputPort.persistHouse(house);
        return houseManagementOutputPort.retrieveHouse(house.getHouseId());
    }

    @Override
    public void removeTenantFromHouse(House house, Tenant tenant) {
        house.removeTenant(tenant);
        houseManagementOutputPort.persistHouse(house);
    }
}
