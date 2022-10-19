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

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    @Override
    public Recipe getRecipeById(Long id) {
        log.info("Listando receita por ID");
        Optional<Recipe> recipe = recipeRepository.findById(id);
        return recipe.orElseThrow(() -> new ObjectNotFoundException("Registro não encontrado na base de dados"));
    }

    @Override
    public List<Recipe> getRecipesByTitleOrTag(String title, String tag) {
        log.info("Listando receitas por título ou tag");
        return recipeRepository.findByTitleOrTagIgnoreCaseContains(title, tag);
    }

    @Override
    public List<Recipe> getRecipes(Integer page, Integer pageSize) {
        log.info("Listando todas as receitas");
        Pageable pageable = PageRequest.of(page, pageSize);
        return recipeRepository.findAll(pageable).getContent();
    }

    @Override
    public Recipe saveRecipe(Recipe recipe) {
        log.info("Salvando uma nova receita {} no banco de dados", recipe.getTitle());
        return recipeRepository.save(recipe);
    }

    @Override
    public void deleteRecipe(Long id) {
        getRecipeById(id);
        try {
            log.info("Excluindo a receita {}", getRecipeById(id).getTitle());
            recipeRepository.deleteById(id);
        } catch (DataIntegrityException e) {
            throw new DataIntegrityException("Não é possível excluir esta receita!");
        }
    }
    
}
