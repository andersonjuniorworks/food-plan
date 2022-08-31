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

import com.andersonjunior.foodplan.domain.models.Training;
import com.andersonjunior.foodplan.domain.models.TrainingGroup;
import com.andersonjunior.foodplan.service.services.TrainingService;

@RestController
@RequestMapping(value = "/api/trainings")
public class TrainingController {
    
    private final TrainingService trainingService;

    public TrainingController(TrainingService trainingService) {
        this.trainingService = trainingService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Training> findById(@PathVariable Long id) {
        Training training = trainingService.findById(id);
        return ResponseEntity.ok().body(training);
    }

    @GetMapping
    public ResponseEntity<List<Training>> findAll(
        @RequestParam(name = "page", required = true, defaultValue = "0") Integer page,
        @RequestParam(name = "size", required = true, defaultValue = "50") Integer size) {
            List<Training> trainings = trainingService.findAll(page, size);
            return ResponseEntity.ok().body(trainings);
    }

    @PostMapping
    public ResponseEntity<String> insert(@Valid @RequestBody Training training) {
        trainingService.insert(training);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(training.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<String> update(@Valid @RequestBody Training training, @PathVariable Long id) {
        trainingService.update(training);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@Valid @PathVariable Long id) {
        trainingService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
