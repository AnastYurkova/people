package com.ku.people.controller;

import com.ku.people.dto.RelationshipDto;
import com.ku.people.dto.RelationshipListDto;
import com.ku.people.dto.RelationshipSaveDto;
import com.ku.people.entity.Relationship;
import com.ku.people.service.RelationshipService;
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
@RequestMapping("/relationships")
@Tag(name = "Relationship controller", description = "The Relationship API")
public class RelationshipController {

    private RelationshipService relationshipService;

    @GetMapping("/{id}")
    @Operation(summary = "Find Relationship by id")
    public RelationshipDto findById(
        @PathVariable("id")
        @Parameter(description = "Relationship id", example = "99", required = true) Long id
    ) {
        return relationshipService.findById(id);
    }

    @GetMapping
    @Operation(summary = "Find all relationships")
    public List<RelationshipListDto> findAll() {
        return relationshipService.findAll();
    }

    @PostMapping
    @Operation(summary = "Save relationship")
    public RelationshipSaveDto save(
        @RequestBody
        @Parameter(description = "Information about saving relationship", required = true) RelationshipSaveDto relationshipSaveDto
    ) {
        return relationshipService.save(relationshipSaveDto);
    }

    @PutMapping
    @Operation(summary = "Update relationship")
    public boolean update
        (@RequestBody
        @Parameter(description = "Information about updating relationship", required = true) RelationshipSaveDto relationshipSaveDto
    ) {
        relationshipService.update(relationshipSaveDto);
        return true;
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "Delete relationship")
    public void delete(
         @PathVariable("id")
         @Parameter(description = "Id of deleting relationship", example = "99", required = true) Long id
    ) {
        relationshipService.delete(id);
    }

    @Autowired
    public void setRelationshipService(RelationshipService relationshipService) {
        this.relationshipService = relationshipService;
    }

}
