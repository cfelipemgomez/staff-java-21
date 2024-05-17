package com.crehana.staff.core.mapper;

import com.crehana.staff.core.model.Employee;
import com.crehana.staff.core.model.Employee.EmployeeBuilder;
import com.crehana.staff.core.model.EmployeeData;
import com.crehana.staff.core.model.Role;
import com.crehana.staff.core.model.Role.RoleBuilder;
import com.crehana.staff.infrastructure.entity.EmployeeEntity;
import com.crehana.staff.infrastructure.entity.RoleEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-17T14:39:32-0500",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 21.0.3 (Amazon.com Inc.)"
)
@Component
public class RoleMapperImpl implements RoleMapper {

    @Override
    public Role entityToModel(RoleEntity entity) {
        if ( entity == null ) {
            return null;
        }

        RoleBuilder role = Role.builder();

        role.name( entity.getName() );
        role.employee( employeeEntityToEmployee( entity.getEmployee() ) );

        return role.build();
    }

    @Override
    public RoleEntity modelToEntity(Role model) {
        if ( model == null ) {
            return null;
        }

        RoleEntity roleEntity = new RoleEntity();

        roleEntity.setName( model.getName() );
        roleEntity.setEmployee( employeeToEmployeeEntity( model.getEmployee() ) );

        return roleEntity;
    }

    @Override
    public Role dtoToModel(EmployeeData modelData) {
        if ( modelData == null ) {
            return null;
        }

        RoleBuilder role = Role.builder();

        role.name( modelData.getRole() );

        return role.build();
    }

    protected Employee employeeEntityToEmployee(EmployeeEntity employeeEntity) {
        if ( employeeEntity == null ) {
            return null;
        }

        EmployeeBuilder employee = Employee.builder();

        employee.id( employeeEntity.getId() );
        employee.name( employeeEntity.getName() );
        employee.lastName( employeeEntity.getLastName() );
        employee.email( employeeEntity.getEmail() );
        employee.phone( employeeEntity.getPhone() );

        return employee.build();
    }

    protected EmployeeEntity employeeToEmployeeEntity(Employee employee) {
        if ( employee == null ) {
            return null;
        }

        EmployeeEntity employeeEntity = new EmployeeEntity();

        employeeEntity.setId( employee.getId() );
        employeeEntity.setName( employee.getName() );
        employeeEntity.setLastName( employee.getLastName() );
        employeeEntity.setEmail( employee.getEmail() );
        employeeEntity.setPhone( employee.getPhone() );

        return employeeEntity;
    }
}
