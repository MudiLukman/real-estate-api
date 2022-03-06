package application.ports.input;

import application.ports.output.PhaseManagementOutputPort;
import application.use_cases.PhaseManagementUseCase;
import domain.entities.House;
import domain.entities.Phase;
import domain.value_objects.Personnel;
import domain.value_objects.PowerSupply;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.UUID;

@ApplicationScoped
public class PhaseManagementInputPort implements PhaseManagementUseCase {

    @Inject PhaseManagementOutputPort phaseManagementOutputPort;

    @Override
    public Phase createPhase(UUID id, String name, Personnel personnel, PowerSupply powerSupply) {
        return new Phase(id, name, personnel, powerSupply);
    }

    @Override
    public Phase updatePhase(UUID id, String name, Personnel personnel, PowerSupply powerSupply) {
        Phase phase = phaseManagementOutputPort.retrievePhase(id);
        phase.setName(name);
        phase.setPersonnel(personnel);
        phase.setPowerSupply(powerSupply);
        phaseManagementOutputPort.persistPhase(phase);
        return phaseManagementOutputPort.retrievePhase(phase.getId());
    }

    @Override
    public Phase retrievePhase(UUID phaseId) {
        return phaseManagementOutputPort.retrievePhase(phaseId);
    }

    @Override
    public Phase addHouseToPhase(Phase phase, House house) {
        phase.addHouse(house);
        phaseManagementOutputPort.persistPhase(phase);
        return phaseManagementOutputPort.retrievePhase(phase.getId());
    }

    @Override
    public void removeHouseFromPhase(Phase phase, House house) {
        phase.removeHouse(house);
        phaseManagementOutputPort.persistPhase(phase);
    }

    @Override
    public Phase persistPhase(Phase phase) {
        phaseManagementOutputPort.persistPhase(phase);
        return phaseManagementOutputPort.retrievePhase(phase.getId());
    }

    @Override
    public void removePhase(UUID phaseId) {
        phaseManagementOutputPort.removePhase(phaseId);
    }
}
