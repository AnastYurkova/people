package com.ku.people.controller;

import com.ku.people.dto.DetailDto;
import com.ku.people.dto.DetailListDto;
import com.ku.people.dto.DetailSaveDto;
import com.ku.people.entity.Detail;
import com.ku.people.service.DetailService;
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
@RequestMapping("/details")
@Tag(name = "Detail controller", description = "The Detail API")
public class DetailController {
    private DetailService detailService;

    @GetMapping("/{id}")
    @Operation(summary = "Find Detail by id")
    public DetailDto findById(
        @PathVariable("id")
        @Parameter(description = "Detail id", example = "99", required = true) Long id
    ) {
        return detailService.findById(id);
    }

    @GetMapping
    @Operation(summary = "Find all details")
    public List<DetailListDto> findAll() {
        return detailService.findAll();
    }

    @PostMapping
    @Operation(summary = "Save detail")
    public DetailSaveDto save(
        @RequestBody
        @Parameter(description = "Information about saving detail", required = true) DetailSaveDto detailSaveDto
    ) {
        return detailService.save(detailSaveDto);
    }

    @PutMapping
    @Operation(summary = "Update detail")
    public void update(
        @RequestBody
        @Parameter(description = "Information about updating detail", required = true) DetailSaveDto detailSaveDto
    ) {
        detailService.update(detailSaveDto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete detail")
    public void delete(
        @PathVariable("id")
        @Parameter(description = "Id of deleting detail", example = "99", required = true) Long id
    ) {
        detailService.delete(id);
    }

    @Autowired
    public void setDetailService(DetailService detailService) {
        this.detailService = detailService;
    }
}
