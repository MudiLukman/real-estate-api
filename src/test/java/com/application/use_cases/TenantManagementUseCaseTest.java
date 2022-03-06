package com.application.use_cases;

import application.use_cases.TenantManagementUseCase;
import domain.entities.Tenant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.UUID;

public class TenantManagementUseCaseTest {

    @Inject
    TenantManagementUseCase tenantManagementUseCase;

    @Test
    @Disabled("Will fix dependency injection")
    public void testCreateTenant() {
        UUID id = UUID.randomUUID();
        Tenant tenant = tenantManagementUseCase.createTenant(
                id, "FN", "LN", "081");

        Assertions.assertEquals(id, tenant.getId());
        Assertions.assertEquals("FN", tenant.getFirstName());
        Assertions.assertEquals("LN", tenant.getLastName());
        Assertions.assertEquals("081", tenant.getPhone());
    }

}
