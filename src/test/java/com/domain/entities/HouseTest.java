package com.domain.entities;

import com.domain.TestHelper;
import domain.entities.Flat;
import domain.entities.House;
import domain.entities.SelfContain;
import domain.entities.Tenant;
import domain.value_objects.Address;
import domain.value_objects.HouseType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class HouseTest {

    private House flat;
    private House selfCon;

    private TestHelper testHelper = new TestHelper();

    @BeforeEach
    public void setup() {
        Set<Tenant> tenantsForFlat = new HashSet<>(testHelper.helpWithTenants(7));
        flat = new Flat(UUID.randomUUID(), new Address("P1F1"));
        tenantsForFlat.forEach(flat::addTenant);

        Set<Tenant> tenantsForSelfCon = new HashSet<>(testHelper.helpWithTenants(1));
        selfCon = new SelfContain(UUID.randomUUID(), new Address("P1F2"));
        tenantsForSelfCon.forEach(selfCon::addTenant);
    }

    @Test
    public void testCreateFlat() {
        UUID id = UUID.randomUUID();
        Address address = new Address("P1H1");
        Set<Tenant> tenants = new HashSet<>();

        House house = new Flat(id, address);
        tenants.forEach(house::addTenant);

        Assertions.assertEquals(HouseType.FLAT, house.getType());
        Assertions.assertEquals(id, house.getHouseId());
        Assertions.assertEquals(address.getNumber(), house.getAddress().getNumber());
        Assertions.assertEquals(0, house.getTenants().size());
    }

    @Test
    public void testAddTenantToFlat() {
        Tenant tenant = new Tenant(UUID.randomUUID(), "Mr.", "Mrs.", "+234");
        flat.addTenant(tenant);

        Assertions.assertTrue(flat.getTenants().contains(tenant));
    }

    @Test
    public void testAddTenantToSelfContain() {
        Tenant tenant = new Tenant(UUID.randomUUID(), "Mr.", "Mrs.", "+234");
        selfCon.addTenant(tenant);

        Assertions.assertTrue(selfCon.getTenants().contains(tenant));
    }

    @Test
    public void testAddTenantToFlat_shouldFailWhenMaxTenantsExceeded() {
        //Adding this tenant just so 'flat.tenants size is 7 (one close to max)
        flat.addTenant(new Tenant(UUID.randomUUID(), "One", "More", "028"));

        Exception exception = Assertions.assertThrows(IllegalStateException.class, () -> {
            flat.addTenant(new Tenant(UUID.randomUUID(), "Second", "More", "028"));
        });

        Assertions.assertEquals("Max number of tenants allowed reached.", exception.getMessage());
    }

    @Test
    public void testAddTenantToSelfCon_shouldFailWhenMaxTenantsExceeded() {
        //Adding this tenant just so 'selfCon.tenants size is 2 (one close to max)
        selfCon.addTenant(new Tenant(UUID.randomUUID(), "One", "More", "028"));

        Exception exception = Assertions.assertThrows(IllegalStateException.class, () -> {
            selfCon.addTenant(new Tenant(UUID.randomUUID(), "Second", "More", "028"));
        });

        Assertions.assertEquals("Max number of tenants allowed reached.", exception.getMessage());
    }

    @Test
    public void testCreateSelfContain() {
        UUID id = UUID.randomUUID();
        Address address = new Address("P1H1");

        House house = new SelfContain(id, address);

        Assertions.assertEquals(HouseType.SELF_CON, house.getType());
        Assertions.assertEquals(id, house.getHouseId());
        Assertions.assertEquals(address.getNumber(), house.getAddress().getNumber());
        Assertions.assertEquals(0, house.getTenants().size());
    }
}
