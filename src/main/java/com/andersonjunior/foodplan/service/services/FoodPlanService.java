package com.andersonjunior.foodplan.service.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.andersonjunior.foodplan.domain.models.FoodGroup;
import com.andersonjunior.foodplan.domain.models.FoodPlan;
import com.andersonjunior.foodplan.domain.repositories.FoodPlanRepository;
import com.andersonjunior.foodplan.service.exceptions.DataIntegrityException;
import com.andersonjunior.foodplan.service.exceptions.ObjectNotFoundException;

@Service
public class FoodPlanService {

    private final FoodPlanRepository foodPlanRepository;
    
    public FoodPlanService(FoodPlanRepository foodPlanRepository) {
        this.foodPlanRepository = foodPlanRepository;
    }

    public List<FoodPlan> findAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return foodPlanRepository.findAll(pageable).getContent();
    }

    public FoodPlan findById(Long id) {
        Optional<FoodPlan> user = foodPlanRepository.findById(id);
        return user.orElseThrow(() -> new ObjectNotFoundException("Registro não encontrado na base de dados"));
    }

    public List<FoodPlan> findByFoodGroup(FoodGroup foodGroup) {
        return foodPlanRepository.findByFoodGroup(foodGroup);
    }

    @Transactional
    public FoodPlan insert(FoodPlan user) {
        user.setId(null);
        return foodPlanRepository.save(user);
    }

    @Transactional
    public FoodPlan update(FoodPlan user) {
        FoodPlan newFoodPlan = findById(user.getId());
        updateData(newFoodPlan, user);
        return foodPlanRepository.save(newFoodPlan);
    }

    @Transactional
    public void delete(Long id) {
        findById(id);
        try {
            foodPlanRepository.deleteById(id);
        } catch (DataIntegrityException e) {
            throw new DataIntegrityException("Não é possível excluir este plano!");
        }
    }

    private void updateData(FoodPlan newFoodPlan, FoodPlan foodPlan) {
        newFoodPlan.setTitle(foodPlan.getTitle());
        newFoodPlan.setSubtitle(foodPlan.getSubtitle());
        newFoodPlan.setDescription(foodPlan.getDescription());
        newFoodPlan.setObservation(foodPlan.getObservation());
        newFoodPlan.setFoodGroup(foodPlan.getFoodGroup());
    }

}
