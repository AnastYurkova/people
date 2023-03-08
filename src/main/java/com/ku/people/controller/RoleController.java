package com.ku.people.controller;

import com.ku.people.dto.RoleDto;
import com.ku.people.dto.RoleListDto;
import com.ku.people.dto.RoleSaveDto;
import com.ku.people.entity.Role;
import com.ku.people.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/roles")
@Tag(name = "Role controller", description = "The Role API")
public class RoleController {
    private RoleService roleService;

    @GetMapping("/{id}")
    @Operation(summary = "Find Role by id")
    public RoleDto findById(
        @PathVariable("id")
        @Parameter(description = "Role id", example = "1", required = true) Long id
    ) {
        return roleService.findById(id);
    }

    @GetMapping
    @Operation(summary = "Find all roles")
    public List<RoleListDto> findAll() {
        return roleService.findAll();
    }

    @PostMapping
    @Operation(summary = "Save role")
    public Role save(
        @RequestBody
        @Parameter(description = "Information about saving role", required = true) RoleSaveDto roleSaveDto
    ) {
        return roleService.save(roleSaveDto);
    }

    @PutMapping
    @Operation(summary = "Update role")
    public boolean update(
        @RequestBody
        @Parameter(description = "Information about updating role", required = true) RoleSaveDto roleSaveDto
    ) {
        roleService.update(roleSaveDto);
        return true;
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete role")
    public void delete(
        @Parameter(description = "Id of deleting role", example = "2", required = true) Long id
    ) {
        roleService.delete(id);
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }
}
