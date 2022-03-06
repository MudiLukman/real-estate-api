package application.use_cases;

import domain.entities.Tenant;

import java.util.UUID;

public interface TenantManagementUseCase {
    Tenant createTenant(UUID id, String firstName, String lastName, String phone);
    Tenant retrieveTenant(UUID tenantId);
    Tenant persistTenant(Tenant tenant);
    void deleteTenant(UUID tenantId);
}
