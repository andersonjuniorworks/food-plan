package com.andersonjunior.foodplan.domain.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.andersonjunior.foodplan.domain.models.TrainingGroup;

@Repository
public interface TrainingGroupRepository extends JpaRepository<TrainingGroup, Long> {
    
    List<TrainingGroup> findByNameIgnoreCaseContains(String name);

}
