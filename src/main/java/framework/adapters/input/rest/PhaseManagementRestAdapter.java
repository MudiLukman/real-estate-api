package framework.adapters.input.rest;

import application.use_cases.PhaseManagementUseCase;
import domain.entities.House;
import domain.entities.Phase;
import domain.value_objects.Personnel;
import domain.value_objects.PowerSupply;

import javax.inject.Inject;
import java.util.UUID;

public class PhaseManagementRestAdapter {

    @Inject
    PhaseManagementUseCase phaseManagementUseCase;

    public Phase createPhase(UUID id, String name, Personnel personnel, PowerSupply powerSupply) {
        Phase phase = phaseManagementUseCase.createPhase(id, name, personnel, powerSupply);
        return phaseManagementUseCase.persistPhase(phase);
    }

    public Phase updatePhase(UUID id, String name, Personnel personnel, PowerSupply powerSupply) {
        return phaseManagementUseCase.updatePhase(id, name, personnel, powerSupply);
    }

    public Phase retrievePhase(UUID id) {
        return phaseManagementUseCase.retrievePhase(id);
    }

    public void removePhase(UUID id) {
        phaseManagementUseCase.removePhase(id);
    }

    public Phase addHouseToPhase(Phase phase, House house) {
        return phaseManagementUseCase.addHouseToPhase(phase, house);
    }

    public void removeHouseFromPhase(Phase phase, House house) {
        phaseManagementUseCase.removeHouseFromPhase(phase, house);
    }

}
