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
import com.andersonjunior.foodplan.domain.models.FoodPlan;
import com.andersonjunior.foodplan.service.services.FoodPlanService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/foodPlans")
public class FoodPlanController {
    
    private final FoodPlanService foodPlanService;

    public FoodPlanController(FoodPlanService foodPlanService) {
        this.foodPlanService = foodPlanService;
    }

    @CrossOrigin
    @ApiOperation(value = "Buscar um plano alimentar por ID")
    @GetMapping(value = "/{id}")
    public ResponseEntity<FoodPlan> findById(@PathVariable Long id) {
        FoodPlan foodPlan = foodPlanService.getFoodPlanById(id);
        return ResponseEntity.ok().body(foodPlan);
    }

    @CrossOrigin
    @ApiOperation(value = "Lista todos os planos alimentares")
    @GetMapping
    public ResponseEntity<List<FoodPlan>> findAll(
        @RequestParam(name = "page", required = true, defaultValue = "0") Integer page,
        @RequestParam(name = "size", required = true, defaultValue = "50") Integer size) {
            List<FoodPlan> foodPlans = foodPlanService.getAllFoodPlan(page, size);
            return ResponseEntity.ok().body(foodPlans);
    }

    @CrossOrigin
    @ApiOperation(value = "Lista todos os planos alimentares por grupo")
    @GetMapping(value = "/foodGroup")
    public ResponseEntity<List<FoodPlan>> findByFoodGroup(
        @RequestParam(name = "value", required = true) FoodGroup foodGroup) {
        List<FoodPlan> foodPlans = foodPlanService.getFoodPlanByFoodGroup(foodGroup);
        return ResponseEntity.ok().body(foodPlans);
    }

    @CrossOrigin
    @ApiOperation(value = "Insere um novo plano alimentar")
    @PostMapping
    public ResponseEntity<String> insert(@Valid @RequestBody FoodPlan foodPlan) {
        foodPlanService.saveFoodPlan(foodPlan);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(foodPlan.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @CrossOrigin
    @ApiOperation(value = "Edita um plano alimentar")
    @PutMapping(value = "/{id}")
    public ResponseEntity<String> update(@Valid @RequestBody FoodPlan foodPlan, @PathVariable Long id) {
        foodPlanService.saveFoodPlan(foodPlan);
        return ResponseEntity.ok().build();
    }

    @CrossOrigin
    @ApiOperation(value = "Exclui um plano alimentar")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@Valid @PathVariable Long id) {
        foodPlanService.deleteFoodPlan(id);
        return ResponseEntity.noContent().build();
    }

}
