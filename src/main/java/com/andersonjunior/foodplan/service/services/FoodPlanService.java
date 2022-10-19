package com.andersonjunior.foodplan.service.services;

import java.util.List;

import com.andersonjunior.foodplan.domain.models.FoodGroup;
import com.andersonjunior.foodplan.domain.models.FoodPlan;

public interface FoodPlanService {

    FoodPlan getFoodPlanById(Long id);
    List<FoodPlan> getFoodPlanByFoodGroup(FoodGroup foodGroup);
    List<FoodPlan> getAllFoodPlan(Integer page, Integer size);
    FoodPlan saveFoodPlan(FoodPlan foodPlan);
    void deleteFoodPlan(Long id);

}
