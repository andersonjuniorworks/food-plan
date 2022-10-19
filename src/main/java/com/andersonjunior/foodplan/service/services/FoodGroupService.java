package com.andersonjunior.foodplan.service.services;

import java.util.List;

import com.andersonjunior.foodplan.domain.models.FoodGroup;

public interface FoodGroupService {

    FoodGroup saveFoodGroup(FoodGroup foodGroup);
    void deleteFoodGroup(Long id);
    FoodGroup getFoodGroupById(Long id);
    List<FoodGroup> getFoodGroups(Integer page, Integer size);
    List<FoodGroup> getFoodGroupsByName(String name);    

}
