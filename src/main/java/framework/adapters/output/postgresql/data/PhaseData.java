package framework.adapters.output.postgresql.data;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity(name = "phases")
public class PhaseData {

    @Id
    @Column(name = "id")
    private UUID phaseId;

    @Column(name = "name")
    private String name;

    @OneToOne
    @JoinColumn(name = "personnel_id")
    private PersonnelData personnel;

    @OneToOne
    @JoinColumn(name = "power_supply_id")
    private PowerSupplyData powerSupply;

    @OneToMany(mappedBy = "phase")
    private Set<HouseData> houses;

}
