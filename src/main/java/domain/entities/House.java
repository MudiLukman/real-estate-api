package domain.entities;

import domain.specifications.FamilySizeSpecification;
import domain.value_objects.Address;
import domain.value_objects.HouseType;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
public abstract class House {
    protected UUID houseId;
    protected Address address;
    protected Set<Tenant> tenants;
    protected final HouseType type;
    public final int MAX_MEMBERS;

    protected House(UUID houseId, Address address, Set<Tenant> tenants, HouseType type, int maxMembers) {
        this.houseId = houseId;
        this.address = address;
        this.tenants = tenants;
        this.type = type;
        MAX_MEMBERS = maxMembers;
    }

    public House addTenant(Tenant tenant) {
        var familySizeSpec = new FamilySizeSpecification(this);

        if (!familySizeSpec.isSatisfiedBy(this)) {
            throw new IllegalStateException("Max number of tenants allowed reached.");
        }

        if (this.getTenants() == null) {
            this.setTenants(new HashSet<>());
        }
        this.getTenants().add(tenant);

        return this;
    }

    public void removeTenant(Tenant tenant) {
        this.tenants.remove(tenant);
    }
}
