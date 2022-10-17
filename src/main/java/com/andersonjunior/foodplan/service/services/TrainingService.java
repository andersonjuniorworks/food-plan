package com.andersonjunior.foodplan.service.services;

import java.util.List;

import com.andersonjunior.foodplan.domain.models.Training;

public interface TrainingService {

    Training getTrainingById(Long id);
    List<Training> getTrainings(Integer page, Integer pageSize);
    Training saveTraining(Training training);
    void deleteTraining(Long id);
    
}
