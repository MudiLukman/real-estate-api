package com.application.use_cases;

import application.use_cases.PhaseManagementUseCase;
import domain.entities.Phase;
import domain.value_objects.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.UUID;

public class PhaseManagementUseCaseTest {

    @Inject
    PhaseManagementUseCase phaseManagementUseCase;

    @Test
    @Disabled("Will fix dependency injection")
    public void testCreatePhase() {
        Phase phase = phaseManagementUseCase.createPhase(
                UUID.randomUUID(),
                "Phase One",
                new Personnel("Mr & Mrs", PersonnelType.GATE_KEEPER),
                new PowerSupply(Status.ACTIVE, PowerSupplyType.GENERATOR)
        );

        Assertions.assertEquals("Phase One", phase.getName());
        Assertions.assertEquals("Mr & Mrs", phase.getPersonnel().getName());
        Assertions.assertEquals(PersonnelType.GATE_KEEPER, phase.getPersonnel().getType());
        Assertions.assertEquals(Status.ACTIVE, phase.getPowerSupply().getStatus());
        Assertions.assertEquals(PowerSupplyType.GENERATOR, phase.getPowerSupply().getType());
    }
}
