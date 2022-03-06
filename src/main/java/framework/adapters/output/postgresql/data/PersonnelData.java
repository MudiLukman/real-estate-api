package framework.adapters.output.postgresql.data;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "personnel")
public class PersonnelData {

    @Id
    private UUID id;

    @Column(name = "name")
    private String name;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "type")
    private PersonnelTypeData personnelType;
}
