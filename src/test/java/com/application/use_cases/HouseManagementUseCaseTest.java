package com.application.use_cases;

import application.use_cases.HouseManagementUseCase;
import domain.entities.House;
import domain.entities.Tenant;
import domain.value_objects.Address;
import domain.value_objects.HouseType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.Set;
import java.util.UUID;

public class HouseManagementUseCaseTest {

    @Inject
    HouseManagementUseCase houseManagementUseCase;

    @Test
    @Disabled("Will fix dependency injection")
    public void testCreateFlat() {
        UUID houseId = UUID.randomUUID();
        System.out.println("Object: " + houseManagementUseCase);
        System.out.println("Class: " + houseManagementUseCase.getClass());
        House house = houseManagementUseCase.createHouse(
                houseId, new Address("P1HN"), HouseType.FLAT);

        Assertions.assertEquals(houseId, house.getHouseId());
        Assertions.assertEquals("P1HN", house.getAddress().getNumber());
        Assertions.assertEquals(HouseType.FLAT, house.getType());
        Assertions.assertEquals(0, house.getTenants().size());
    }

    @Test
    @Disabled("Will fix dependency injection")
    public void testCreateSelfCon() {
        UUID houseId = UUID.randomUUID();
        House house = houseManagementUseCase.createHouse(
                houseId, new Address("P1HT"), HouseType.SELF_CON);
        Set<Tenant> tenants = Set.of(
                new Tenant(UUID.randomUUID(), "FN", "LN", "091"),
                new Tenant(UUID.randomUUID(), "FF", "LL", "092")
        );
        tenants.forEach(house::addTenant);

        Assertions.assertEquals(houseId, house.getHouseId());
        Assertions.assertEquals("P1HT", house.getAddress().getNumber());
        Assertions.assertEquals(HouseType.SELF_CON, house.getType());
        Assertions.assertEquals(2, house.getTenants().size());
    }

}
