package framework.adapters.output.postgresql.mapper;

import domain.entities.House;
import domain.entities.Tenant;
import domain.services.HouseOperation;
import domain.value_objects.Address;
import domain.value_objects.HouseType;
import framework.adapters.output.postgresql.data.AddressData;
import framework.adapters.output.postgresql.data.HouseData;
import framework.adapters.output.postgresql.data.HouseTypeData;
import framework.adapters.output.postgresql.data.TenantData;

import java.util.Set;
import java.util.stream.Collectors;

public class HouseJPAMapper {

    private static final HouseOperation houseOperation = new HouseOperation();

    public static House toDomain(HouseData houseData) {
        Set<Tenant> tenants = houseData.getTenants().stream()
                .map(TenantJPAMapper::toDomain)
                .collect(Collectors.toSet());

        return houseOperation.createHouse(
                houseData.getId(),
                HouseType.valueOf(houseData.getHouseType().toString()),
                new Address(houseData.getAddress().getNumber()),
                tenants);
    }

    public static HouseData toData(House house) {
        Set<TenantData> tenantData = house.getTenants().stream()
                .map(TenantJPAMapper::toData)
                .collect(Collectors.toSet());

        AddressData addressData = new AddressData();
        addressData.setNumber(house.getAddress().getNumber());

        HouseData houseData = new HouseData();
        houseData.setId(house.getHouseId());
        houseData.setHouseType(HouseTypeData.valueOf(house.getType().toString()));
        houseData.setAddress(addressData);
        houseData.setTenants(tenantData);
        return houseData;
    }

}
