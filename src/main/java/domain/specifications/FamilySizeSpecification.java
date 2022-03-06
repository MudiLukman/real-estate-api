package domain.specifications;

import domain.entities.House;

public class FamilySizeSpecification implements AbstractSpecification<House> {

    private final int maxNumberOfTenants;

    public FamilySizeSpecification(House house) {
        maxNumberOfTenants = house.MAX_MEMBERS;
    }

    @Override
    public boolean isSatisfiedBy(House house) {
        return house.getTenants().size() < maxNumberOfTenants;
    }
}
