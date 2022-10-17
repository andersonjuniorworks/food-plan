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

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class FoodGroupServiceImpl implements FoodGroupService {

    private final FoodGroupRepository foodGroupRepository;

    @Override
    public FoodGroup getFoodGroupById(Long id) {
        Optional<FoodGroup> foodGroup = foodGroupRepository.findById(id);
        return foodGroup.orElseThrow(() -> new ObjectNotFoundException("Registro não encontrado na base de dados"));
    }

    @Override
    public List<FoodGroup> getFoodGroups(Integer page, Integer size) {
        log.info("Listando todos os grupos de alimentos");
        Pageable pageable = PageRequest.of(page, size);
        return foodGroupRepository.findAll(pageable).getContent();
    }

    @Override
    public List<FoodGroup> getFoodGroupsByName(String name) {
        log.info("Listando os grupos alimentares por nome");
        return foodGroupRepository.findByNameContainsIgnoreCase(name);
    }

    @Override
    public FoodGroup saveFoodGroup(FoodGroup foodGroup) {
        log.info("Salvando um novo grupo de alimentos {} no banco de dados", foodGroup.getName());
        return foodGroupRepository.save(foodGroup);
    }

    @Override
    public void deleteFoodGroup(Long id) {
        getFoodGroupById(id);
        try {
            log.info("Excluindo o grupo de alimentos {}", getFoodGroupById(id).getName());
            foodGroupRepository.deleteById(id);
        } catch (DataIntegrityException e) {
            throw new DataIntegrityException("Não é possível excluir este grupo de alimentos!");
        }
    }

}
