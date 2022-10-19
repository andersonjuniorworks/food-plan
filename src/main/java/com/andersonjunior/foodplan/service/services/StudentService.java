package com.andersonjunior.foodplan.service.services;

import java.util.List;

import com.andersonjunior.foodplan.domain.models.Student;

public interface StudentService {

    Student getStudentById(Long id);
    List<Student> getStudents(Integer page, Integer pageSize);
    Student saveStudent(Student student);
    void deleteStudent(Long id);
    
}
