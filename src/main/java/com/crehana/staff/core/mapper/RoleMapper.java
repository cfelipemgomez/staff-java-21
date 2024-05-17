package com.crehana.staff.core.mapper;

import com.crehana.staff.core.model.EmployeeData;
import com.crehana.staff.core.model.Role;
import com.crehana.staff.infrastructure.entity.RoleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    Role entityToModel(RoleEntity entity);
    RoleEntity modelToEntity(Role model);

    @Mapping(source = "role", target = "name")
    Role dtoToModel(EmployeeData modelData);
}
