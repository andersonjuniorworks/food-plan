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

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class TrainingServiceImpl implements TrainingService {

    private final TrainingRepository trainingRepository;
    
    @Override
    public Training getTrainingById(Long id) {
        log.info("Listando treino por ID");
        Optional<Training> training = trainingRepository.findById(id);
        return training.orElseThrow(() -> new ObjectNotFoundException("Registro não encontrado na base de dados"));
    }

    @Override
    public List<Training> getTrainings(Integer page, Integer pageSize) {
        log.info("Listando todos os treinos");
        Pageable pageable = PageRequest.of(page, pageSize);
        return trainingRepository.findAll(pageable).getContent();
    }

    @Override
    public Training saveTraining(Training training) {
        log.info("Salvando o treino {}", training.getDescription());
        return trainingRepository.save(training);
    }

    @Override
    public void deleteTraining(Long id) {
        getTrainingById(id);
        try {
            log.info("Excluindo o treino {}", getTrainingById(id).getDescription());
            trainingRepository.deleteById(id);
        } catch (DataIntegrityException e) {
            throw new DataIntegrityException("Não é possível excluir este treino!");
        } 
    }

    @Override
    public List<Training> getTrainingByGroup(TrainingGroup trainingGroup) {
        log.info("Listando todos os treinos por grupo");
        return trainingRepository.findByTrainingGroup(trainingGroup);
    }
    
}
