package framework.adapters.output.postgresql.mapper;

import domain.value_objects.PowerSupply;
import domain.value_objects.PowerSupplyType;
import domain.value_objects.Status;
import framework.adapters.output.postgresql.data.PowerSupplyData;
import framework.adapters.output.postgresql.data.PowerSupplyTypeData;
import framework.adapters.output.postgresql.data.StatusData;

import java.util.UUID;

public class PowerSupplyJPAMapper {

    public static PowerSupply toDomain(PowerSupplyData powerSupplyData) {
        return new PowerSupply(
                Status.valueOf(powerSupplyData.getStatus().toString()),
                PowerSupplyType.valueOf(powerSupplyData.getPowerSupplyType().toString()));
    }

    public static PowerSupplyData toData(PowerSupply powerSupply) {
        PowerSupplyData powerSupplyData = new PowerSupplyData();
        powerSupplyData.setId(UUID.randomUUID());
        powerSupplyData.setStatus(StatusData.valueOf(powerSupply.getStatus().toString()));
        powerSupplyData.setPowerSupplyType(PowerSupplyTypeData.valueOf(powerSupply.getType().toString()));
        return powerSupplyData;
    }

}
