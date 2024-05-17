package com.crehana.staff.infrastructure.adapter;

import com.crehana.staff.core.mapper.RoleMapper;
import com.crehana.staff.core.model.Role;
import com.crehana.staff.infrastructure.adapter.repository.RoleRepository;
import com.crehana.staff.infrastructure.entity.RoleEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RolePersistenceImplTest {

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private RolePersistenceImpl rolePersistenceImpl;

    private Role role;
    private RoleEntity roleEntity;

    @BeforeEach
    void setUp() {
        role = new Role();
        role.setName("Developer");

        roleEntity = new RoleEntity();
        roleEntity.setName("Developer");
    }

    @Test
    void testSaveSuccess() {
        when(roleRepository.save(any(RoleEntity.class))).thenReturn(roleEntity);

        Role savedRole = rolePersistenceImpl.save(role);

        verify(roleRepository, times(1)).save(any(RoleEntity.class));
        assertEquals(role, savedRole);
    }

    @Test
    void testSaveThrowsException() {
        when(roleRepository.save(any(RoleEntity.class))).thenThrow(new RuntimeException("Database error"));

        assertThrows(RuntimeException.class, () -> rolePersistenceImpl.save(role));

        verify(roleRepository, times(1)).save(any(RoleEntity.class));
    }
}