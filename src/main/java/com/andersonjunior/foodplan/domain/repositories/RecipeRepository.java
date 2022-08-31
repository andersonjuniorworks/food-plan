package com.andersonjunior.foodplan.domain.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.andersonjunior.foodplan.domain.models.Recipe;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    List<Recipe> findByTitleOrTagIgnoreCaseContains(String title, String tag);
    
}
