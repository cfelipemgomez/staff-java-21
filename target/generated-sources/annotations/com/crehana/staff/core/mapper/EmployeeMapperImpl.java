package com.crehana.staff.core.mapper;

import com.crehana.staff.core.model.Employee;
import com.crehana.staff.core.model.Employee.EmployeeBuilder;
import com.crehana.staff.core.model.EmployeeData;
import com.crehana.staff.infrastructure.entity.EmployeeEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-17T14:39:32-0500",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 21.0.3 (Amazon.com Inc.)"
)
@Component
public class EmployeeMapperImpl implements EmployeeMapper {

    @Override
    public Employee employeeEntityToEmployee(EmployeeEntity entity) {
        if ( entity == null ) {
            return null;
        }

        EmployeeBuilder employee = Employee.builder();

        employee.id( entity.getId() );
        employee.name( entity.getName() );
        employee.lastName( entity.getLastName() );
        employee.email( entity.getEmail() );
        employee.phone( entity.getPhone() );

        return employee.build();
    }

    @Override
    public EmployeeEntity employeeToEmployeeEntity(Employee model) {
        if ( model == null ) {
            return null;
        }

        EmployeeEntity employeeEntity = new EmployeeEntity();

        employeeEntity.setId( model.getId() );
        employeeEntity.setName( model.getName() );
        employeeEntity.setLastName( model.getLastName() );
        employeeEntity.setEmail( model.getEmail() );
        employeeEntity.setPhone( model.getPhone() );

        return employeeEntity;
    }

    @Override
    public Employee employeeDataToEmployee(EmployeeData employeeData) {
        if ( employeeData == null ) {
            return null;
        }

        EmployeeBuilder employee = Employee.builder();

        employee.id( employeeData.getId() );
        employee.name( employeeData.getName() );
        employee.lastName( employeeData.getLastName() );
        employee.email( employeeData.getEmail() );
        employee.phone( employeeData.getPhone() );

        return employee.build();
    }
}
