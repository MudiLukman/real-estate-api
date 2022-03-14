package framework.adapters.input.rest;

import application.use_cases.TenantManagementUseCase;
import domain.entities.Tenant;

import javax.inject.Inject;
import java.util.UUID;

public class TenantManagementRestAdapter {

    @Inject
    TenantManagementUseCase tenantManagementUseCase;

    public Tenant createTenant(UUID id, String firstName, String lastName, String phone) {
        Tenant tenant = tenantManagementUseCase.createTenant(id, firstName, lastName, phone);
        return tenantManagementUseCase.persistTenant(tenant);
    }

    public void deleteTenant(UUID id) {
        tenantManagementUseCase.deleteTenant(id);
    }

}
