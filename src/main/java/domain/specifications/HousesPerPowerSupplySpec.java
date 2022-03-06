package domain.specifications;

import domain.entities.Phase;
import domain.value_objects.PowerSupply;
import domain.value_objects.PowerSupplyType;

public class HousesPerPowerSupplySpec implements AbstractSpecification<Phase> {

    private final int maxNumberOfHouses;

    public HousesPerPowerSupplySpec(PowerSupply powerSupply) {
        maxNumberOfHouses = getMaxNumberOfHousesForType(powerSupply.getType());
    }

    @Override
    public boolean isSatisfiedBy(Phase phase) {
        return phase.getHouses().size() < maxNumberOfHouses;
    }

    private static int getMaxNumberOfHousesForType(PowerSupplyType type) {
        if (type.equals(PowerSupplyType.SOLAR)) {
            return 10;
        } else if (type.equals(PowerSupplyType.GENERATOR)) {
            return  20;
        } else {
            return 120;
        }
    }

}
