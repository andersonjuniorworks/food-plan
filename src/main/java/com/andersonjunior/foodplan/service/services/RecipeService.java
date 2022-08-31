package com.andersonjunior.foodplan.service.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.andersonjunior.foodplan.domain.models.Recipe;
import com.andersonjunior.foodplan.domain.repositories.RecipeRepository;
import com.andersonjunior.foodplan.service.exceptions.DataIntegrityException;
import com.andersonjunior.foodplan.service.exceptions.ObjectNotFoundException;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;
    
    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }   
        
    public List<Recipe> findAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return recipeRepository.findAll(pageable).getContent();
    }

    public Recipe findById(Long id) {
        Optional<Recipe> recipe = recipeRepository.findById(id);
        return recipe.orElseThrow(() -> new ObjectNotFoundException("Registro não encontrado na base de dados"));
    }

    @Transactional
    public Recipe insert(Recipe recipe) {
        recipe.setId(null);
        return recipeRepository.save(recipe);
    }

    @Transactional
    public Recipe update(Recipe recipe) {
        Recipe newRecipe = findById(recipe.getId());
        updateData(newRecipe, recipe);
        return recipeRepository.save(newRecipe);
    }

    @Transactional
    public void delete(Long id) {
        findById(id);
        try {
            recipeRepository.deleteById(id);
        } catch (DataIntegrityException e) {
            throw new DataIntegrityException("Não é possível excluir este plano!");
        }
    }

    private void updateData(Recipe newRecipe, Recipe recipe) {
        newRecipe.setTitle(recipe.getTitle());
        newRecipe.setSubtitle(recipe.getSubtitle());
        newRecipe.setContent(recipe.getContent());
        newRecipe.setTag(recipe.getTag());
        newRecipe.setAuthor(recipe.getAuthor());
    }

}
