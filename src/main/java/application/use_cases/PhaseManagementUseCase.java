package application.use_cases;

import domain.entities.House;
import domain.entities.Phase;
import domain.value_objects.Personnel;
import domain.value_objects.PowerSupply;

import java.util.UUID;

public interface PhaseManagementUseCase {

    Phase createPhase(UUID id, String name, Personnel personnel, PowerSupply powerSupply);

    Phase updatePhase(UUID id, String name, Personnel personnel, PowerSupply powerSupply);

    Phase retrievePhase(UUID phaseId);

    Phase addHouseToPhase(Phase phase, House house);

    void removeHouseFromPhase(Phase phase, House house);

    Phase persistPhase(Phase phase);

    void removePhase(UUID id);
}
