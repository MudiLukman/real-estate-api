package application.ports.output;

import domain.entities.House;

import java.util.UUID;

public interface HouseManagementOutputPort {
    House retrieveHouse(UUID houseId);
    void persistHouse(House house);
}
