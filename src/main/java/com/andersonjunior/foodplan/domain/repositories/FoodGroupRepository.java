package com.andersonjunior.foodplan.domain.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.andersonjunior.foodplan.domain.models.FoodGroup;

@Repository
public interface FoodGroupRepository extends JpaRepository<FoodGroup, Long> {
    
    List<FoodGroup> findByNameContainsIgnoreCase(String name);

}
