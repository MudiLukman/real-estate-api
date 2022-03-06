package com.domain;

import domain.entities.Flat;
import domain.entities.House;
import domain.entities.Phase;
import domain.entities.Tenant;
import domain.value_objects.Address;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TestHelper {

    public List<UUID> helpWithIds(int size) {
        List<UUID> ids = new ArrayList<>();
        for (int i = 1; i <= size; i++) {
            ids.add(UUID.randomUUID());
        }
        return ids;
    }

    public List<Tenant> helpWithTenants(int size) {
        List<Tenant> tenants = new ArrayList<>();
        for (int i = 1; i <= size; i++) {
            tenants.add(new Tenant(UUID.randomUUID(), i + "Test", "Test" + i, "+234" + i));
        }
        return tenants;
    }

    public void addHousesToPhase(Phase phase, int numberOfHouses) {
        for (int i = 1; i <= numberOfHouses; i++) {
            phase.addHouse(new Flat(UUID.randomUUID(), new Address("P1H" + i)));
        }
    }

    public List<House> helpWithHouses(int size) {
        List<House> houses = new ArrayList<>();
        for (int i = 1; i <= size; i++) {
            houses.add(new Flat(UUID.randomUUID(), new Address("P1H1")));
        }
        return houses;
    }

}
