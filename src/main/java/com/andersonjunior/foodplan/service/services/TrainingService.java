package com.andersonjunior.foodplan.service.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.andersonjunior.foodplan.domain.models.Training;
import com.andersonjunior.foodplan.domain.models.TrainingGroup;
import com.andersonjunior.foodplan.domain.repositories.TrainingRepository;
import com.andersonjunior.foodplan.service.exceptions.DataIntegrityException;
import com.andersonjunior.foodplan.service.exceptions.ObjectNotFoundException;

@Service
public class TrainingService {

    private final TrainingRepository trainingRepository;

    public TrainingService(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    public List<Training> findAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return trainingRepository.findAll(pageable).getContent();
    }

    public Training findById(Long id) {
        Optional<Training> training = trainingRepository.findById(id);
        return training.orElseThrow(() -> new ObjectNotFoundException("Registro não encontrado na base de dados"));
    }

    @Transactional
    public Training insert(Training training) {
        training.setId(null);
        return trainingRepository.save(training);
    }

    @Transactional
    public Training update(Training training) {
        Training newTraining = findById(training.getId());
        updateData(newTraining, training);
        return trainingRepository.save(newTraining);
    }

    @Transactional
    public void delete(Long id) {
        findById(id);
        try {
            trainingRepository.deleteById(id);
        } catch (DataIntegrityException e) {
            throw new DataIntegrityException("Não é possível excluir este treino!");
        }
    }

    private void updateData(Training newTraining, Training training) {
        newTraining.setDescription(training.getDescription());
        newTraining.setSeries(training.getSeries());
        newTraining.setRepeat(training.getRepeat());
        newTraining.setRepose(training.getRepose());
        newTraining.setWeight(training.getWeight());
        newTraining.setMethod(training.getMethod());
        newTraining.setCadence(training.getCadence());
    }
    
}
