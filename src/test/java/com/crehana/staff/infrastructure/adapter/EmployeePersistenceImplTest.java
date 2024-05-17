package com.crehana.staff.infrastructure.adapter;

import com.crehana.staff.core.exception.DataBaseException;
import com.crehana.staff.core.model.Employee;
import com.crehana.staff.infrastructure.adapter.repository.EmployeeRepository;
import com.crehana.staff.infrastructure.entity.EmployeeEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeePersistenceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeePersistenceImpl employeePersistenceImpl;

    private Employee employee;
    private EmployeeEntity employeeEntity;

    @BeforeEach
    void setUp() {
        employee = new Employee();
        employee.setId("1L");
        employee.setName("John");
        employee.setLastName("Doe");
        employee.setEmail("john.doe@example.com");
        employee.setPhone("123456789");

        employeeEntity = new EmployeeEntity();
        employeeEntity.setId("1L");
        employeeEntity.setName("John");
        employeeEntity.setLastName("Doe");
        employeeEntity.setEmail("john.doe@example.com");
        employeeEntity.setPhone("123456789");
    }

    @Test
    void testSaveSuccess() {
        when(employeeRepository.save(any(EmployeeEntity.class))).thenReturn(employeeEntity);

        Employee savedEmployee = employeePersistenceImpl.save(employee);

        verify(employeeRepository, times(1)).save(any(EmployeeEntity.class));
        assertEquals(employee, savedEmployee);
    }

    @Test
    void testSaveThrowsException() {
        when(employeeRepository.save(any(EmployeeEntity.class))).thenThrow(new RuntimeException("Database error"));

        assertThrows(DataBaseException.class, () -> employeePersistenceImpl.save(employee));

        verify(employeeRepository, times(1)).save(any(EmployeeEntity.class));
    }
}