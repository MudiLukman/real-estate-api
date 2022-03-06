package framework.adapters.output;

import application.ports.output.TenantManagementOutputPort;
import domain.entities.Tenant;
import framework.adapters.output.postgresql.data.TenantData;
import framework.adapters.output.postgresql.mapper.TenantJPAMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.UUID;

@ApplicationScoped
public class TenantManagementOutputAdapter implements TenantManagementOutputPort {

    @PersistenceContext EntityManager entityManager;

    @Override
    public Tenant retrieveTenant(UUID tenantId) {
        TenantData tenantData = entityManager.getReference(TenantData.class, tenantId);
        return TenantJPAMapper.toDomain(tenantData);
    }

    @Override
    public Tenant persistTenant(Tenant tenant) {
        TenantData tenantData = TenantJPAMapper.toData(tenant);
        entityManager.persist(tenantData);
        return retrieveTenant(tenant.getId());
    }

    @Override
    public void deleteTenant(UUID tenantId) {
        TenantData tenantData = entityManager.find(TenantData.class, tenantId);
        entityManager.remove(tenantData);
    }
}
