package com.domain.entities;

import com.domain.TestHelper;
import domain.entities.*;
import domain.value_objects.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class PhaseTest {

    private Phase phase;
    private TestHelper testHelper = new TestHelper();

    @BeforeEach
    public void setup() {
        Personnel personnel = new Personnel("Mr. X", PersonnelType.GATE_KEEPER);
        PowerSupply powerSupply = new PowerSupply(Status.ACTIVE, PowerSupplyType.GENERATOR);

        phase = new Phase(UUID.randomUUID(), "Init Test Phase", personnel, powerSupply);
    }

    @Test
    public void testCreatePhase() {
        Personnel personnel = new Personnel("Mr. Domain", PersonnelType.GATE_KEEPER);
        PowerSupply powerSupply = new PowerSupply(Status.ACTIVE, PowerSupplyType.GENERATOR);

        Phase phase = new Phase(UUID.randomUUID(), "Phase One", personnel, powerSupply);

        Assertions.assertEquals("Phase One", phase.getName());
        Assertions.assertEquals(personnel.getName(), phase.getPersonnel().getName());
        Assertions.assertEquals(personnel.getType(), phase.getPersonnel().getType());
        Assertions.assertEquals(powerSupply.getStatus(), phase.getPowerSupply().getStatus());
        Assertions.assertEquals(powerSupply.getType(), phase.getPowerSupply().getType());
        Assertions.assertEquals(0, phase.getHouses().size());
    }

    @Test
    public void testAddHouseToPhase() {
        House house = new Flat(UUID.randomUUID(), new Address("P1H1"));

        phase.addHouse(house);

        Assertions.assertEquals(
                house.getHouseId(),
                phase.getHouses().iterator().next().getHouseId()
        );
    }

    @Test
    public void testAddHouse_shouldFailWhenMaxNumberOfHousesPerPowerSupplyIsExceeded() {
        testHelper.addHousesToPhase(phase, 20);

        House house = new Flat(UUID.randomUUID(), new Address("P1H1"));

        Exception exception = Assertions.assertThrows(IllegalStateException.class, () -> {
            phase.addHouse(house);
        });

        Assertions.assertEquals("Max number of houses for power supply reached", exception.getMessage());
    }

    @Test
    public void testAddHouse_shouldFailWhenMaxNumberOfHousesPerPhaseIsExceeded() {
        //update power supply type to STATE
        phase.setPowerSupply(new PowerSupply(phase.getPowerSupply().getStatus(), PowerSupplyType.STATE));
        testHelper.addHousesToPhase(phase, 100);

        House house = new SelfContain(UUID.randomUUID(), new Address("P1H1"));

        Exception exception = Assertions.assertThrows(IllegalStateException.class, () -> {
            phase.addHouse(house);
        });

        Assertions.assertEquals("Max number of houses in phase reached", exception.getMessage());
    }

    @Test
    public void testRemoveHouse() {
        House house = new Flat(UUID.randomUUID(), new Address("P1H1"));
        phase.addHouse(house);

        phase.removeHouse(house);

        Assertions.assertFalse(phase.getHouses().contains(house));
    }

    @Test
    public void testRemoveHouse_shouldFailIfHouseHasTenants() {
        House house = new SelfContain(UUID.randomUUID(), new Address("P1H2"));
        house.addTenant(new Tenant(UUID.randomUUID(), "First", "Second", "+123"));
        phase.addHouse(house);

        Exception exception = Assertions.assertThrows(IllegalStateException.class, () -> {
            phase.removeHouse(house);
        });

        Assertions.assertEquals("Cannot remove house with tenants from phase", exception.getMessage());
    }
}
