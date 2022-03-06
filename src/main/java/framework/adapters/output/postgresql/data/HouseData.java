package framework.adapters.output.postgresql.data;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "houses")
public class HouseData {

    @Id
    @Column(name = "id")
    private UUID id;

    @Embedded
    private AddressData address;

    @OneToMany(mappedBy = "house")
    private Set<TenantData> tenants;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "type")
    private HouseTypeData houseType;

    @ManyToOne
    @JoinColumn(name = "phase_id")
    private PhaseData phase;
}
