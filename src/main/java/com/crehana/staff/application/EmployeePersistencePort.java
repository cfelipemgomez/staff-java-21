package com.crehana.staff.application;

import com.crehana.staff.core.model.Employee;

public interface EmployeePersistencePort {

    Employee save(Employee employee);
}
