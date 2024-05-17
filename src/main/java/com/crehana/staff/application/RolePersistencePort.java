package com.crehana.staff.application;

import com.crehana.staff.core.model.Role;

public interface RolePersistencePort {

    Role save(Role role);
}
