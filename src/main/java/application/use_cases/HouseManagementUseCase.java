package application.use_cases;

import domain.entities.House;
import domain.entities.Tenant;
import domain.value_objects.Address;
import domain.value_objects.HouseType;

import java.util.UUID;

public interface HouseManagementUseCase {
    House createHouse(UUID houseId, Address address, HouseType type);
    House retrieveHouse(UUID id);
    House persistHouse(House house);
    House addTenantToHouse(House house, Tenant tenant);
    void removeTenantFromHouse(House house, Tenant tenant);
}
