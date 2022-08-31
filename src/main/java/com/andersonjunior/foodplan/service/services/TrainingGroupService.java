package com.andersonjunior.foodplan.service.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.andersonjunior.foodplan.domain.models.TrainingGroup;
import com.andersonjunior.foodplan.domain.repositories.TrainingGroupRepository;
import com.andersonjunior.foodplan.service.exceptions.DataIntegrityException;
import com.andersonjunior.foodplan.service.exceptions.ObjectNotFoundException;

@Service
public class TrainingGroupService {

    private final TrainingGroupRepository trainingGroupRepository;

    public TrainingGroupService(TrainingGroupRepository trainingGroupRepository) {
        this.trainingGroupRepository = trainingGroupRepository;
    }

    public List<TrainingGroup> findAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return trainingGroupRepository.findAll(pageable).getContent();
    }

    public TrainingGroup findById(Long id) {
        Optional<TrainingGroup> trainingGroup = trainingGroupRepository.findById(id);
        return trainingGroup.orElseThrow(() -> new ObjectNotFoundException("Registro não encontrado na base de dados"));
    }

    @Transactional
    public TrainingGroup insert(TrainingGroup trainingGroup) {
        trainingGroup.setId(null);
        return trainingGroupRepository.save(trainingGroup);
    }

    @Transactional
    public TrainingGroup update(TrainingGroup trainingGroup) {
        TrainingGroup newTrainingGroup = findById(trainingGroup.getId());
        updateData(newTrainingGroup, trainingGroup);
        return trainingGroupRepository.save(newTrainingGroup);
    }

    @Transactional
    public void delete(Long id) {
        findById(id);
        try {
            trainingGroupRepository.deleteById(id);
        } catch (DataIntegrityException e) {
            throw new DataIntegrityException("Não é possível excluir este plano!");
        }
    }

    private void updateData(TrainingGroup newTrainingGroup, TrainingGroup trainingGroup) {
        newTrainingGroup.setName(trainingGroup.getName());
    }
    
}
