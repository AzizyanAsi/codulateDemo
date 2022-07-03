package com.example.demo.controller;

import com.example.demo.converter.RoleConverter;
import com.example.demo.converter.dataTransferObjects.RoleRequestDTO;
import com.example.demo.converter.dataTransferObjects.RoleResponseDTO;
import com.example.demo.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/role")
public class RoleController {
    private final RoleService roleService;
    private final RoleConverter roleConverter;

    @PostMapping("/create")
    public ResponseEntity<RoleResponseDTO> createRole(@RequestBody @Valid RoleRequestDTO roleRequest) {
        return ResponseEntity.ok(roleConverter.convertToDTO(roleService.createRole(roleRequest)));

    }

    @PutMapping("/{id}")
    public ResponseEntity<RoleResponseDTO> updateRole(@PathVariable Long id,
                                                          @RequestBody @Valid RoleRequestDTO roleRequest) {
        return ResponseEntity.ok(roleConverter.convertToDTO(roleService.update(id, roleRequest)));
    }

    @GetMapping
    public List<? extends RoleResponseDTO> getAllRoles() {
        return roleConverter.bulkConvertToDTO(roleService.getAllRoles());
    }

    @GetMapping("/search")
    public ResponseEntity<RoleResponseDTO> searchRoleByName(@RequestParam("roleName") String name){
        return ResponseEntity.ok(roleConverter.convertToDTO(roleService.findByName(name)));
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<RoleResponseDTO> searchRoleById(@PathVariable Long id){
        return ResponseEntity.ok(roleConverter.convertToDTO(roleService.findById(id)));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteRole(@PathVariable Long id) {
        return ResponseEntity.ok(roleService.deleteById(id));
    }
}
