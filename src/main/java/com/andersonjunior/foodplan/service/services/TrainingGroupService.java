package com.andersonjunior.foodplan.service.services;

import java.util.List;

import com.andersonjunior.foodplan.domain.models.TrainingGroup;

public interface TrainingGroupService {

    TrainingGroup getTrainingGroupById(Long id);
    List<TrainingGroup> getTrainingGroups(Integer page, Integer pageSize);
    List<TrainingGroup> getTrainingGroupsByName(String name);
    TrainingGroup saveTrainingGroup(TrainingGroup trainingGroup);
    void deleteTrainingGroup(Long id);
    
}
