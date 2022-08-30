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
        Optional<FoodGroup> user = foodGroupRepository.findById(id);
        return user.orElseThrow(() -> new ObjectNotFoundException("Registro não encontrado na base de dados"));
    }

    @Transactional
    public FoodGroup insert(FoodGroup user) {
        user.setId(null);
        return foodGroupRepository.save(user);
    }

    @Transactional
    public FoodGroup update(FoodGroup user) {
        FoodGroup newUser = findById(user.getId());
        updateData(newUser, user);
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
