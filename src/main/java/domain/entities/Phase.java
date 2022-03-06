package domain.entities;

import domain.specifications.EmptyHouseSpecification;
import domain.specifications.HousesPerPowerSupplySpec;
import domain.specifications.MaxHousesPerPhaseSpec;
import domain.value_objects.Personnel;
import domain.value_objects.PowerSupply;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
public class Phase {
    private UUID id;
    private String name;
    private Personnel personnel;
    private PowerSupply powerSupply;
    private Set<House> houses;

    public Phase(UUID id, String name, Personnel personnel, PowerSupply powerSupply) {
        this.id = id;
        this.name = name;
        this.personnel = personnel;
        this.powerSupply = powerSupply;
        this.houses = new HashSet<>();
    }

    public Phase addHouse(House house) {
        var maxHousesPerPhaseSpec = new MaxHousesPerPhaseSpec();
        var maxHousesPerPowerSupply = new HousesPerPowerSupplySpec(this.getPowerSupply());

        if (!maxHousesPerPhaseSpec.isSatisfiedBy(this)) {
            throw new IllegalStateException("Max number of houses in phase reached");
        }

        if (!maxHousesPerPowerSupply.isSatisfiedBy(this)) {
            throw new IllegalStateException("Max number of houses for power supply reached");
        }

        if (this.getHouses() == null) {
            this.setHouses(new HashSet<>());
        }
        this.getHouses().add(house);
        return this;
    }

    public void removeHouse(House house) {
        var emptyHouseSpec = new EmptyHouseSpecification();
        if (!emptyHouseSpec.isSatisfiedBy(house)) {
            throw new IllegalStateException("Cannot remove house with tenants from phase");
        }
        this.houses.remove(house);
    }

}
