package framework.adapters.input;

import application.use_cases.HouseManagementUseCase;
import application.use_cases.TenantManagementUseCase;
import domain.entities.House;
import domain.entities.Tenant;
import domain.value_objects.Address;
import domain.value_objects.HouseType;

import javax.inject.Inject;
import java.util.UUID;

public class HouseManagementGenericAdapter {

    @Inject
    HouseManagementUseCase houseManagementUseCase;
    @Inject
    TenantManagementUseCase tenantManagementUseCase;

    public House createHouse(UUID id, Address address, HouseType type) {
        House house = houseManagementUseCase.createHouse(id, address, type);
        return houseManagementUseCase.persistHouse(house);
    }

    public House addTenantToHouse(UUID houseId, UUID tenantId) {
        House house = houseManagementUseCase.retrieveHouse(houseId);
        Tenant tenant = tenantManagementUseCase.retrieveTenant(tenantId);
        houseManagementUseCase.addTenantToHouse(house, tenant);
        return houseManagementUseCase.persistHouse(house);
    }

    public void removeTenantFromHouse(House house, Tenant tenant) {
        houseManagementUseCase.removeTenantFromHouse(house, tenant);
    }
}
