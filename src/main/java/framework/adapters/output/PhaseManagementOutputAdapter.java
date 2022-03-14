package framework.adapters.output;

import application.ports.output.PhaseManagementOutputPort;
import domain.entities.Phase;
import framework.adapters.output.postgresql.data.PhaseData;
import framework.adapters.output.postgresql.mapper.PhaseJPAMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.UUID;

@ApplicationScoped
public class PhaseManagementOutputAdapter implements PhaseManagementOutputPort {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Phase retrievePhase(UUID phaseId) {
        PhaseData phaseData = entityManager.find(PhaseData.class, phaseId);
        return PhaseJPAMapper.toDomain(phaseData);
    }

    @Override
    @Transactional
    public void removePhase(UUID phaseId) {
        PhaseData phaseData = entityManager.getReference(PhaseData.class, phaseId);
        entityManager.remove(phaseData);
    }

    @Override
    @Transactional
    public void persistPhase(Phase phase) {
        PhaseData phaseData = PhaseJPAMapper.toData(phase);
        entityManager.persist(phaseData);
    }
}
