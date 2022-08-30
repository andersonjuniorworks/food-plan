package com.andersonjunior.foodplan.domain.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.andersonjunior.foodplan.domain.models.FoodGroup;
import com.andersonjunior.foodplan.domain.models.FoodPlan;

@Repository
public interface FoodPlanRepository extends JpaRepository<FoodPlan, Long> {
    
    List<FoodPlan> findByFoodGroup(FoodGroup foodGroup);

}
