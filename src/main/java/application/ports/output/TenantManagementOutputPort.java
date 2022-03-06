package application.ports.output;

import domain.entities.Tenant;

import java.util.UUID;

public interface TenantManagementOutputPort {
    Tenant retrieveTenant(UUID tenantId);
    Tenant persistTenant(Tenant tenant);
    void deleteTenant(UUID tenantId);
}
