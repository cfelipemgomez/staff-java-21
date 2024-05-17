package com.crehana.staff.infrastructure.adapter;

import com.crehana.staff.application.RolePersistencePort;
import com.crehana.staff.core.mapper.RoleMapper;
import com.crehana.staff.core.model.Role;
import com.crehana.staff.infrastructure.adapter.repository.RoleRepository;
import com.crehana.staff.infrastructure.entity.RoleEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RolePersistenceImpl implements RolePersistencePort {

    private final RoleRepository roleRepository;

    @Override
    public Role save(Role role) {
        RoleEntity roleEntity = RoleMapper.INSTANCE.modelToEntity(role);
        return RoleMapper.INSTANCE.entityToModel(roleRepository.save(roleEntity));
    }
}
