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

import com.andersonjunior.foodplan.domain.models.Training;
import com.andersonjunior.foodplan.domain.models.TrainingGroup;
import com.andersonjunior.foodplan.service.services.TrainingService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/trainings")
public class TrainingController {
    
    private final TrainingService trainingService;

    public TrainingController(TrainingService trainingService) {
        this.trainingService = trainingService;
    }

    @CrossOrigin
    @ApiOperation(value = "Lista um treino por ID")
    @GetMapping(value = "/{id}")
    public ResponseEntity<Training> findById(@PathVariable Long id) {
        Training training = trainingService.getTrainingById(id);
        return ResponseEntity.ok().body(training);
    }

    @CrossOrigin
    @ApiOperation(value = "Lista todos os treinos")
    @GetMapping
    public ResponseEntity<List<Training>> findAll(
        @RequestParam(name = "page", required = true, defaultValue = "0") Integer page,
        @RequestParam(name = "size", required = true, defaultValue = "50") Integer size) {
            List<Training> trainings = trainingService.getTrainings(page, size);
            return ResponseEntity.ok().body(trainings);
    }

    @CrossOrigin
    @ApiOperation(value = "Lista todos os treinos por grupo")
    @GetMapping(value = "/group")
    public ResponseEntity<List<Training>> findByTrainingGroup(
        @RequestParam(name = "trainingGroup", required = true) TrainingGroup trainingGroup
    ) {
        List<Training> trainings = trainingService.getTrainingByGroup(trainingGroup);
        return ResponseEntity.ok().body(trainings);
    }

    @CrossOrigin
    @ApiOperation(value = "Insere um novo treino")
    @PostMapping
    public ResponseEntity<String> insert(@Valid @RequestBody Training training) {
        trainingService.saveTraining(training);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(training.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @CrossOrigin
    @ApiOperation(value = "Editar um treino")
    @PutMapping(value = "/{id}")
    public ResponseEntity<String> update(@Valid @RequestBody Training training, @PathVariable Long id) {
        trainingService.saveTraining(training);
        return ResponseEntity.ok().build();
    }

    @CrossOrigin
    @ApiOperation(value = "Exclui um treino")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@Valid @PathVariable Long id) {
        trainingService.deleteTraining(id);
        return ResponseEntity.noContent().build();
    }

}
