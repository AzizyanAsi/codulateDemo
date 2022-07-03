package com.example.demo.converter.impl;

import com.example.demo.converter.RoleConverter;
import com.example.demo.converter.dataTransferObjects.RoleRequestDTO;
import com.example.demo.converter.dataTransferObjects.RoleResponseDTO;
import com.example.demo.domain.Role;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RoleConverterImpl implements RoleConverter {

    @Override
    public Role convertToEntity(RoleRequestDTO roleRequest) {
        if (roleRequest == null) {
            return null;
        }
        Role entity = new Role();
        entity.setName(roleRequest.getName());
        entity.setDescription(roleRequest.getDescription());
        return entity;
    }

    @Override
    public RoleResponseDTO convertToDTO(Role role) {
        RoleResponseDTO roleResponse=new RoleResponseDTO();
        roleResponse.setId(role.getId());
        roleResponse.setName(role.getName());
        roleResponse.setDescription(role.getDescription());
        return roleResponse;
    }

    @Override
    public List<RoleResponseDTO> bulkConvertToDTO(List<Role> roles) {
        return roles.stream().map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<Role> bulkConvertToEntity(List<RoleRequestDTO> roleRequest) {
        return roleRequest.stream().map(this::convertToEntity)
                .collect(Collectors.toList());
    }
}
