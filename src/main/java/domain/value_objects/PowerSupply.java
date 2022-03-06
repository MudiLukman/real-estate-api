package domain.value_objects;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PowerSupply {
    private Status status;
    private PowerSupplyType type;
}
