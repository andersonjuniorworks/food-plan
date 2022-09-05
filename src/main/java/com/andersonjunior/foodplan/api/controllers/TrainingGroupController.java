package com.andersonjunior.foodplan.api.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
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

import com.andersonjunior.foodplan.domain.models.TrainingGroup;
import com.andersonjunior.foodplan.service.services.TrainingGroupService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/trainingGroups")
public class TrainingGroupController {
    
    private final TrainingGroupService trainingGroupService;

    public TrainingGroupController(TrainingGroupService trainingGroupService) {
        this.trainingGroupService = trainingGroupService;
    }

    @ApiOperation(value = "Busca um grupo por ID")
    @GetMapping(value = "/{id}")
    public ResponseEntity<TrainingGroup> findById(@PathVariable Long id) {
        TrainingGroup trainingGroup = trainingGroupService.findById(id);
        return ResponseEntity.ok().body(trainingGroup);
    }

    @ApiOperation(value = "Lista todos os grupos de treinos")
    @GetMapping
    public ResponseEntity<List<TrainingGroup>> findAll(
        @RequestParam(name = "page", required = true, defaultValue = "0") Integer page,
        @RequestParam(name = "size", required = true, defaultValue = "50") Integer size) {
            List<TrainingGroup> trainingGroups = trainingGroupService.findAll(page, size);
            return ResponseEntity.ok().body(trainingGroups);
    }

    @ApiOperation(value = "Insere um novo grupo de treinos")
    @PostMapping
    public ResponseEntity<String> insert(@Valid @RequestBody TrainingGroup trainingGroup) {
        trainingGroupService.insert(trainingGroup);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(trainingGroup.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @ApiOperation(value = "Edita um grupo de treino")
    @PutMapping(value = "/{id}")
    public ResponseEntity<String> update(@Valid @RequestBody TrainingGroup trainingGroup, @PathVariable Long id) {
        trainingGroupService.update(trainingGroup);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Exclui um grupo de treino")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@Valid @PathVariable Long id) {
        trainingGroupService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
