package framework.adapters.output;

import application.ports.output.HouseManagementOutputPort;
import domain.entities.House;
import framework.adapters.output.postgresql.data.HouseData;
import framework.adapters.output.postgresql.mapper.HouseJPAMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.UUID;

@ApplicationScoped
public class HouseManagementOutputAdapter implements HouseManagementOutputPort {

    @PersistenceContext EntityManager entityManager;

    @Override
    public House retrieveHouse(UUID houseId) {
        HouseData houseData = entityManager.getReference(HouseData.class, houseId);
        return HouseJPAMapper.toDomain(houseData);
    }

    @Override
    @Transactional
    public void persistHouse(House house) {
        HouseData houseData = HouseJPAMapper.toData(house);
        entityManager.persist(houseData);
    }
}
