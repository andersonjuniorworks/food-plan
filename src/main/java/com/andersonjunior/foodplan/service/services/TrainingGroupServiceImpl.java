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

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class TrainingGroupServiceImpl implements TrainingGroupService {

    private final TrainingGroupRepository trainingGroupRepository;

    @Override
    public TrainingGroup getTrainingGroupById(Long id) {
        log.info("Listando grupo de treino por ID");
        Optional<TrainingGroup> trainingGroup = trainingGroupRepository.findById(id);
        return trainingGroup.orElseThrow(() -> new ObjectNotFoundException("Registro não encontrado na base de dados"));
    }

    @Override
    public List<TrainingGroup> getTrainingGroups(Integer page, Integer pageSize) {
        log.info("Listando todos os grupos de treino");
        Pageable pageable = PageRequest.of(page, pageSize);
        return trainingGroupRepository.findAll(pageable).getContent();
    }

    @Override
    public List<TrainingGroup> getTrainingGroupsByName(String name) {
        log.info("Listando grupos de treino por nome {}", name);
        return trainingGroupRepository.findByNameIgnoreCaseContains(name);
    }

    @Override
    public TrainingGroup saveTrainingGroup(TrainingGroup trainingGroup) {
        log.info("Salvando um novo grupo de treino {}", trainingGroup.getName());
        return trainingGroupRepository.save(trainingGroup);
    }

    @Override
    public void deleteTrainingGroup(Long id) {
        getTrainingGroupById(id);
        try {
            log.info("Excluindo o grupo de treino {}", getTrainingGroupById(id).getName());
            trainingGroupRepository.deleteById(id);
        } catch (DataIntegrityException e) {
            throw new DataIntegrityException("Não é possível excluir este grupo de treinos!");
        }
    }
    
}
