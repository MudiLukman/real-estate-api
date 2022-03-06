package framework.adapters.output.postgresql.mapper;

import domain.value_objects.Personnel;
import domain.value_objects.PersonnelType;
import framework.adapters.output.postgresql.data.PersonnelData;
import framework.adapters.output.postgresql.data.PersonnelTypeData;

import java.util.UUID;

public class PersonnelJPAMapper {

    public static Personnel toDomain(PersonnelData personnelData) {
        return new Personnel(personnelData.getName(), PersonnelType.valueOf(personnelData.getPersonnelType().toString()));
    }

    public static PersonnelData toData(Personnel personnel) {
        PersonnelData personnelData = new PersonnelData();
        personnelData.setId(UUID.randomUUID());
        personnelData.setName(personnel.getName());
        personnelData.setPersonnelType(PersonnelTypeData.valueOf(personnel.getType().toString()));
        return personnelData;
    }

}
