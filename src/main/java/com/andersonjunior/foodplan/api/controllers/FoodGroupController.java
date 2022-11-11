package com.andersonjunior.foodplan.api.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.andersonjunior.foodplan.domain.models.FoodGroup;
import com.andersonjunior.foodplan.service.services.FoodGroupService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/foodGroups")
@RequiredArgsConstructor
public class FoodGroupController {
    
    private final FoodGroupService foodGroupService;

    @CrossOrigin
    @ApiOperation(value = "Buscar um grupo alimentar por ID")
    @GetMapping(value = "/{id}")
    public ResponseEntity<FoodGroup> findById(@PathVariable Long id) {
        FoodGroup foodGroup = foodGroupService.getFoodGroupById(id);
        return ResponseEntity.ok().body(foodGroup);
    }

    @CrossOrigin
    @ApiOperation(value = "Lista todos os grupos alimentares")
    @GetMapping
    public ResponseEntity<List<FoodGroup>> findAll(
        @RequestParam(name = "page", required = true, defaultValue = "0") Integer page,
        @RequestParam(name = "size", required = true, defaultValue = "50") Integer size) {
            List<FoodGroup> foodGroups = foodGroupService.getFoodGroups(page, size);
            return ResponseEntity.ok().body(foodGroups);
    }

    @CrossOrigin
    @ApiOperation(value = "Insere um novo grupo alimentar")
    @PostMapping
    public ResponseEntity<String> insert(@Valid @RequestBody FoodGroup foodGroup) {
        foodGroupService.saveFoodGroup(foodGroup);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(foodGroup.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @CrossOrigin
    @ApiOperation(value = "Edita um grupo alimentar")
    @PutMapping(value = "/{id}")
    public ResponseEntity<String> update(@Valid @RequestBody FoodGroup foodGroup, @PathVariable Long id) {
        foodGroupService.saveFoodGroup(foodGroup);
        return ResponseEntity.ok().build();
    }

    @CrossOrigin
    @ApiOperation(value = "Exclui um grupo alimentar")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@Valid @PathVariable Long id) {
        foodGroupService.deleteFoodGroup(id);
        return ResponseEntity.noContent().build();
    }

}
