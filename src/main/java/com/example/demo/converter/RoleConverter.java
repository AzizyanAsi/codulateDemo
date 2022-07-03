package com.example.demo.converter;

import com.example.demo.converter.dataTransferObjects.RoleRequestDTO;
import com.example.demo.converter.dataTransferObjects.RoleResponseDTO;
import com.example.demo.domain.Role;

import java.util.List;

public interface RoleConverter {

    Role convertToEntity(RoleRequestDTO roleRequest);

    RoleResponseDTO convertToDTO(Role role);

    List<RoleResponseDTO> bulkConvertToDTO(List<Role> roles);

    List<Role> bulkConvertToEntity(List<RoleRequestDTO> roleRequest);
}
