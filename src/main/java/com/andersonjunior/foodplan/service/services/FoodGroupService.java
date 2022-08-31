package com.andersonjunior.foodplan.service.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.andersonjunior.foodplan.domain.models.FoodGroup;
import com.andersonjunior.foodplan.domain.repositories.FoodGroupRepository;
import com.andersonjunior.foodplan.service.exceptions.DataIntegrityException;
import com.andersonjunior.foodplan.service.exceptions.ObjectNotFoundException;

@Service
public class FoodGroupService {

    private final FoodGroupRepository foodGroupRepository;
    
    public FoodGroupService(FoodGroupRepository foodGroupRepository) {
        this.foodGroupRepository = foodGroupRepository;
    }

    public List<FoodGroup> findAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return foodGroupRepository.findAll(pageable).getContent();
    }

    public FoodGroup findById(Long id) {
        Optional<FoodGroup> foodGroup = foodGroupRepository.findById(id);
        return foodGroup.orElseThrow(() -> new ObjectNotFoundException("Registro não encontrado na base de dados"));
    }

    @Transactional
    public FoodGroup insert(FoodGroup foodGroup) {
        foodGroup.setId(null);
        return foodGroupRepository.save(foodGroup);
    }

    @Transactional
    public FoodGroup update(FoodGroup foodGroup) {
        FoodGroup newUser = findById(foodGroup.getId());
        updateData(newUser, foodGroup);
        return foodGroupRepository.save(newUser);
    }

    @Transactional
    public void delete(Long id) {
        findById(id);
        try {
            foodGroupRepository.deleteById(id);
        } catch (DataIntegrityException e) {
            throw new DataIntegrityException("Não é possível excluir este grupo!");
        }
    }

    private void updateData(FoodGroup newFoodGroup, FoodGroup foodGroup) {
        newFoodGroup.setName(foodGroup.getName());
    }

}
