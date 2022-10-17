package com.andersonjunior.foodplan.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.andersonjunior.foodplan.domain.models.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}
