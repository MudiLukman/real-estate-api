package com.domain.services;

import com.domain.TestHelper;
import domain.entities.House;
import domain.entities.Tenant;
import domain.services.HouseOperation;
import domain.value_objects.Address;
import domain.value_objects.HouseType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class HouseOperationTest {

    private static HouseOperation houseOperation = new HouseOperation();
    private TestHelper testHelper = new TestHelper();

    @Test
    public void testCreateFlat() {
        UUID houseId = UUID.randomUUID();
        Address address = new Address("P1H1");
        Set<Tenant> tenants = new HashSet<>();
        List<UUID> tenantsIds = testHelper.helpWithIds(8);
        for (int i = 0; i < 8; i++) {
            tenants.add(new Tenant(tenantsIds.get(i), "F" + i, "L" + i, "+234" + i));
        }

        House house = houseOperation.createHouse(houseId, HouseType.FLAT, address, tenants);

        Assertions.assertEquals(houseId, house.getHouseId());
        Assertions.assertEquals(HouseType.FLAT, house.getType());
        Assertions.assertEquals(address, house.getAddress());
        Assertions.assertEquals(8, house.getTenants().size());
    }

    @Test
    public void testCreateSelfContain() {
        UUID houseId = UUID.randomUUID();
        Address address = new Address("P1H2");
        Set<Tenant> tenants = new HashSet<>();
        List<UUID> tenantsIds = testHelper.helpWithIds(2);
        for (int i = 0; i < 2; i++) {
            tenants.add(new Tenant(tenantsIds.get(i), "F" + i, "L" + i, "+234" + i));
        }

        House house = houseOperation.createHouse(houseId, HouseType.SELF_CON, address, tenants);

        Assertions.assertEquals(houseId, house.getHouseId());
        Assertions.assertEquals(HouseType.SELF_CON, house.getType());
        Assertions.assertEquals(address, house.getAddress());
        Assertions.assertEquals(2, house.getTenants().size());
    }
}
