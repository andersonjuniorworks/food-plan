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

import com.andersonjunior.foodplan.domain.models.Recipe;
import com.andersonjunior.foodplan.service.services.RecipeService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/recipes")
public class RecipeController {
    
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @ApiOperation(value = "Lista uma receita por ID")
    @GetMapping(value = "/{id}")
    public ResponseEntity<Recipe> findById(@PathVariable Long id) {
        Recipe recipe = recipeService.findById(id);
        return ResponseEntity.ok().body(recipe);
    }

    @ApiOperation(value = "Lista todos as receitas")
    @GetMapping
    public ResponseEntity<List<Recipe>> findAll(
        @RequestParam(name = "page", required = true, defaultValue = "0") Integer page,
        @RequestParam(name = "size", required = true, defaultValue = "50") Integer size) {
            List<Recipe> recipes = recipeService.findAll(page, size);
            return ResponseEntity.ok().body(recipes);
    }

    @ApiOperation(value = "Insere uma nova receita")
    @PostMapping
    public ResponseEntity<String> insert(@Valid @RequestBody Recipe recipe) {
        recipeService.insert(recipe);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(recipe.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @ApiOperation(value = "Edita uma receita")
    @PutMapping(value = "/{id}")
    public ResponseEntity<String> update(@Valid @RequestBody Recipe recipe, @PathVariable Long id) {
        recipeService.update(recipe);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Exclui uma receita")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@Valid @PathVariable Long id) {
        recipeService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
