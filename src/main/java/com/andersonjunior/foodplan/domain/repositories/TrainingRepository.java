package com.andersonjunior.foodplan.domain.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.andersonjunior.foodplan.domain.models.Training;
import com.andersonjunior.foodplan.domain.models.TrainingGroup;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Long> {

    List<Training> findByTrainingGroup(TrainingGroup trainingGroup);

}
