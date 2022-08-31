package com.andersonjunior.foodplan.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.andersonjunior.foodplan.domain.models.Training;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Long> {
    
}
