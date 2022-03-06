package framework.adapters.input.rest;

import application.use_cases.HouseManagementUseCase;
import application.use_cases.TenantManagementUseCase;
import domain.entities.House;
import domain.entities.Tenant;
import domain.value_objects.Address;
import domain.value_objects.HouseType;
import framework.adapters.input.rest.mapper.AddressMapper;
import framework.adapters.input.rest.model.HouseDTO;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.UUID;

@Path("houses")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class HouseManagementRestAdapter {

    @Inject
    HouseManagementUseCase houseManagementUseCase;
    @Inject
    TenantManagementUseCase tenantManagementUseCase;

    @POST
    public House createHouse(@Valid HouseDTO houseDTO) {
        Address address = AddressMapper.toAddress(houseDTO.getAddress());
        HouseType type = HouseType.valueOf(houseDTO.getHouseType().toString());
        House house = houseManagementUseCase.createHouse(houseDTO.getId(), address, type);
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
