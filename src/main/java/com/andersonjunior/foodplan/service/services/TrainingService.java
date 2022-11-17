package com.andersonjunior.foodplan.service.services;

import java.util.List;

import com.andersonjunior.foodplan.domain.models.Training;
import com.andersonjunior.foodplan.domain.models.TrainingGroup;

public interface TrainingService {

    Training getTrainingById(Long id);
    List<Training> getTrainings(Integer page, Integer pageSize);
    List<Training> getTrainingByGroup(TrainingGroup trainingGroup);
    Training saveTraining(Training training);
    void deleteTraining(Long id);
    
}
