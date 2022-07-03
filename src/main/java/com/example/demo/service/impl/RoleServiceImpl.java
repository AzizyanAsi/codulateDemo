package com.example.demo.service.impl;

import com.example.demo.converter.RoleConverter;
import com.example.demo.converter.dataTransferObjects.RoleRequestDTO;
import com.example.demo.domain.Role;
import com.example.demo.domain.User;
import com.example.demo.exception.RoleAlreadyExistException;
import com.example.demo.exception.RoleNotFoundException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.repository.RoleRepository;
import com.example.demo.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RoleServiceImpl.class);

    private final RoleRepository roleRepository;
    private final RoleConverter roleConverter;

    @Override
    @Transactional
    public Role createRole(RoleRequestDTO roleRequest) {
        LOGGER.debug("Requested to create role for name : {}", roleRequest.getName());
        return roleRepository.save(roleConverter.convertToEntity(roleRequest));
    }

    @Override
    @Transactional
    public Role update(Long id, RoleRequestDTO roleRequest) {
        LOGGER.info("Requested to update a role with id {}", id);
        final Role role = this.roleRepository.findById(id)
                .orElseThrow(() -> new RoleNotFoundException("No role found by this id {}", id));
        role.setName(roleRequest.getName());
        role.setDescription(roleRequest.getDescription());
        return roleRepository.save(role);
    }

    @Override
    @Transactional(readOnly = true)
    public Role findByName(String roleName) {
        LOGGER.info("In findByRoleName requested to get the role with roleName {}", roleName);
        return this.roleRepository.findRoleByName(roleName)
                .orElseThrow(() -> new RoleNotFoundException("No role found by this name", roleName));
    }

    @Override
    @Transactional(readOnly = true)
    public Role findById(Long id) {
        LOGGER.debug("In findById requested to get the role with  id: {}", id);
        return this.roleRepository.findById(id)
                .orElseThrow(() -> new RoleNotFoundException("Not found role by this id: {}", id));
    }


    @Override
    @Transactional
    public Boolean isRoleNameAlreadyInUse(String name) {
        return roleRepository.findRoleByName(name).isPresent();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Role> getAllRoles() {
        final List<Role> roles = this.roleRepository.findAll();
        LOGGER.debug("In find allRoles : {} roles found", roles.size());
        return roles;
    }

    @Override
    @Transactional
    public boolean deleteById(Long id) {
        LOGGER.info("Requested to delete a role with id {}", id);
        if (!roleRepository.existsById(id)) {
            throw new RoleNotFoundException("No role found by this id", id);
        }
        roleRepository.deleteById(id);
        LOGGER.info(" Role with id {} successfully deleted", id);
        return true;
    }
}
