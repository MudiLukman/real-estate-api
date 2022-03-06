package domain.value_objects;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Personnel {
    private String name;
    private PersonnelType type;
}
