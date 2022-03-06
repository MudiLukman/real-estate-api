package framework.adapters.output.postgresql.data;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "power_supplies")
public class PowerSupplyData {

    @Id
    private UUID id;

    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private StatusData status;

    @Column(name = "type")
    @Enumerated(value = EnumType.STRING)
    private PowerSupplyTypeData powerSupplyType;
}
