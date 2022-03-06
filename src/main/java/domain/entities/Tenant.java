package domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class Tenant {
    private UUID id;
    private String firstName;
    private String lastName;
    private String phone;
}
