package domain.specifications;

import domain.entities.House;

public class EmptyHouseSpecification implements AbstractSpecification<House> {

    @Override
    public boolean isSatisfiedBy(House house) {
        return house.getTenants() == null || house.getTenants().isEmpty();
    }

}
