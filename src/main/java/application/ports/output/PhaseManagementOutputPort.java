package application.ports.output;

import domain.entities.Phase;

import java.util.UUID;

public interface PhaseManagementOutputPort {
    Phase retrievePhase(UUID phaseId);
    void removePhase(UUID phaseId);
    void persistPhase(Phase phase);
}
