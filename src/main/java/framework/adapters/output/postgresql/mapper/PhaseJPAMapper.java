package framework.adapters.output.postgresql.mapper;

import domain.entities.House;
import domain.entities.Phase;
import domain.value_objects.Personnel;
import domain.value_objects.PowerSupply;
import framework.adapters.output.postgresql.data.HouseData;
import framework.adapters.output.postgresql.data.PersonnelData;
import framework.adapters.output.postgresql.data.PhaseData;
import framework.adapters.output.postgresql.data.PowerSupplyData;

import java.util.Set;
import java.util.stream.Collectors;

public class PhaseJPAMapper {

    public static Phase toDomain(PhaseData phaseData) {
        Personnel personnel = PersonnelJPAMapper.toDomain(phaseData.getPersonnel());
        PowerSupply powerSupply = PowerSupplyJPAMapper.toDomain(phaseData.getPowerSupply());
        Set<House> houses = phaseData.getHouses().stream()
                .map(HouseJPAMapper::toDomain)
                .collect(Collectors.toSet());

        Phase phase = new Phase(phaseData.getPhaseId(), phaseData.getName(), personnel, powerSupply);
        phase.setHouses(houses);
        return phase;
    }

    public static PhaseData toData(Phase phase) {
        PersonnelData personnelData = PersonnelJPAMapper.toData(phase.getPersonnel());
        PowerSupplyData powerSupplyData = PowerSupplyJPAMapper.toData(phase.getPowerSupply());
        Set<HouseData> houseData = phase.getHouses().stream()
                .map(HouseJPAMapper::toData)
                .collect(Collectors.toSet());

        PhaseData phaseData = new PhaseData();
        phaseData.setPhaseId(phase.getId());
        phaseData.setName(phase.getName());
        phaseData.setPersonnel(personnelData);
        phaseData.setPowerSupply(powerSupplyData);
        phaseData.setHouses(houseData);
        return phaseData;
    }

}
