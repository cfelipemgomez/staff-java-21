package com.crehana.staff.infrastructure.adapter;

import com.crehana.staff.application.EmployeePersistencePort;
import com.crehana.staff.core.exception.DataBaseException;
import com.crehana.staff.core.mapper.EmployeeMapper;
import com.crehana.staff.core.model.Employee;
import com.crehana.staff.infrastructure.adapter.repository.EmployeeRepository;
import com.crehana.staff.infrastructure.entity.EmployeeEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class EmployeePersistenceImpl implements EmployeePersistencePort {

    private final EmployeeRepository employeeRepository;

    @Override
    public Employee save(Employee employee) {
        try {
            EmployeeEntity employeeEntity = EmployeeMapper.INSTANCE.employeeToEmployeeEntity(employee);
            return EmployeeMapper.INSTANCE.employeeEntityToEmployee(employeeRepository.save(employeeEntity));
        } catch (RuntimeException e){
            throw new DataBaseException(e.getMessage());
        }

    }
}
