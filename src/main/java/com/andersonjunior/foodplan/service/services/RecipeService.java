package com.andersonjunior.foodplan.service.services;

import java.util.List;

import com.andersonjunior.foodplan.domain.models.Recipe;

public interface RecipeService {

    Recipe getRecipeById(Long id);
    List<Recipe> getRecipesByTitleOrTag(String title, String tag);
    List<Recipe> getRecipes(Integer page, Integer pageSize);
    Recipe saveRecipe(Recipe recipe);
    void deleteRecipe(Long id);

}
