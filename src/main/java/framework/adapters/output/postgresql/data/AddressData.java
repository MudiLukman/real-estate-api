package framework.adapters.output.postgresql.data;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Getter
@Setter
@Embeddable
public class AddressData {
    private String number;
}
