package com.crehana.staff.core.mapper;

import com.crehana.staff.core.model.Employee;
import com.crehana.staff.core.model.EmployeeData;
import com.crehana.staff.infrastructure.entity.EmployeeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    Employee employeeEntityToEmployee(EmployeeEntity entity);

    EmployeeEntity employeeToEmployeeEntity(Employee model);

    Employee employeeDataToEmployee(EmployeeData employeeData);

}
