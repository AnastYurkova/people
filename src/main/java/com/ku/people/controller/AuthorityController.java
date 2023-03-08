package com.ku.people.controller;

import com.ku.people.dto.AuthorityDto;
import com.ku.people.dto.AuthorityListDto;
import com.ku.people.dto.AuthoritySaveDto;
import com.ku.people.entity.Authority;
import com.ku.people.service.AuthorityService;
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
@RequestMapping("/authorities")
@Tag(name = "Authority controller", description = "The Authority API")
public class AuthorityController {
    private AuthorityService authorityService;

    @GetMapping("/{id}")
    @Operation(summary = "Find Authority by id")
    public AuthorityDto findById(
        @PathVariable("id")
        @Parameter(description = "Authority id", example = "99", required = true) Long id
    ) {
        return authorityService.findById(id);
    }

    @GetMapping
    @Operation(summary = "Find all authorities")
    public List<AuthorityListDto> findAll() {
        return authorityService.findAll();
    }

    @PostMapping
    @Operation(summary = "Save role")
    public Authority save(@RequestBody
        @Parameter(description = "Information about saving authority", required = true) AuthoritySaveDto authoritySaveDto
    ) {
        return authorityService.save(authoritySaveDto);
    }

    @PutMapping
    @Operation(summary = "Update role")
    public boolean update(@RequestBody
        @Parameter(description = "Information about updating authority", required = true) AuthoritySaveDto authoritySaveDto
    ) {
        authorityService.update(authoritySaveDto);
        return true;
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete authority")
    public void delete(
        @PathVariable("id")
        @Parameter(description = "Id of deleting authority", example = "99", required = true) Long id
    ) {
        authorityService.delete(id);
    }

    @Autowired
    public void setAuthorityService(AuthorityService authorityService) {
        this.authorityService = authorityService;
    }
}
