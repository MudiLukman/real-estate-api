package domain.specifications;

import domain.entities.Phase;

public class MaxHousesPerPhaseSpec implements AbstractSpecification<Phase> {

    private int maxNumberOfHouses = 100;

    @Override
    public boolean isSatisfiedBy(Phase phase) {
        return phase.getHouses().size() < maxNumberOfHouses;
    }
}
