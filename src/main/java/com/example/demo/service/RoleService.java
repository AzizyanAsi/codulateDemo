package com.example.demo.service;

import com.example.demo.converter.dataTransferObjects.RoleRequestDTO;
import com.example.demo.domain.Role;

import java.util.List;

public interface RoleService {
    Role createRole(RoleRequestDTO roleRequest);

    Role update(Long id, RoleRequestDTO request);

    Role findByName(String roleName);

    Role findById(Long id);

    Boolean isRoleNameAlreadyInUse(String name);

    List<Role> getAllRoles();

    boolean deleteById(Long id);
}
