package application.ports.input;

import application.ports.output.TenantManagementOutputPort;
import application.use_cases.TenantManagementUseCase;
import domain.entities.Tenant;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.UUID;

@ApplicationScoped
public class TenantManagementInputPort implements TenantManagementUseCase {

    @Inject TenantManagementOutputPort tenantManagementOutputPort;

    @Override
    public Tenant createTenant(UUID id, String firstName, String lastName, String phone) {
        return new Tenant(id, firstName, lastName, phone);
    }

    @Override
    public Tenant retrieveTenant(UUID id) {
        return tenantManagementOutputPort.retrieveTenant(id);
    }

    @Override
    public Tenant persistTenant(Tenant tenant) {
        return tenantManagementOutputPort.persistTenant(tenant);
    }

    @Override
    public void deleteTenant(UUID id) {
        tenantManagementOutputPort.deleteTenant(id);
    }
}
