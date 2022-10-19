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

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class FoodPlanServiceImpl implements FoodPlanService {

    private final FoodPlanRepository foodPlanRepository;
    
    @Override
    public FoodPlan getFoodPlanById(Long id) {
        log.info("Listando plano alimentar por ID");
        Optional<FoodPlan> foodPlan = foodPlanRepository.findById(id);
        return foodPlan.orElseThrow(() -> new ObjectNotFoundException("Registro não encontrado na base de dados"));
    }

    @Override
    public List<FoodPlan> getFoodPlanByFoodGroup(FoodGroup foodGroup) {
        log.info("Listando planos alimentares por grupo");
        return foodPlanRepository.findByFoodGroup(foodGroup);
    }

    @Override
    public List<FoodPlan> getAllFoodPlan(Integer page, Integer size) {
        log.info("Listando todos os planos alimentares");
        Pageable pageable = PageRequest.of(page, size);
        return foodPlanRepository.findAll(pageable).getContent();
    }

    @Override
    public FoodPlan saveFoodPlan(FoodPlan foodPlan) {
        log.info("Salvando um plano alimentar {} no banco de dados", foodPlan.getTitle());
        return foodPlanRepository.save(foodPlan);
    }

    @Override
    public void deleteFoodPlan(Long id) {
        getFoodPlanById(id);
        try {
            log.info("Excluindo o plano alimentar {}", getFoodPlanById(id).getTitle());
            foodPlanRepository.deleteById(id);
        } catch (DataIntegrityException e) {
            throw new DataIntegrityException("Não é possível excluir este plano alimentar!");
        }
    }
    
}
