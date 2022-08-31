package com.andersonjunior.foodplan.service.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.andersonjunior.foodplan.domain.models.Student;
import com.andersonjunior.foodplan.domain.repositories.StudentRepository;
import com.andersonjunior.foodplan.service.exceptions.DataIntegrityException;
import com.andersonjunior.foodplan.service.exceptions.ObjectNotFoundException;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }   
        
    public List<Student> findAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return studentRepository.findAll(pageable).getContent();
    }

    public Student findById(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        return student.orElseThrow(() -> new ObjectNotFoundException("Registro não encontrado na base de dados"));
    }

    @Transactional
    public Student insert(Student student) {
        student.setId(null);
        return studentRepository.save(student);
    }

    @Transactional
    public Student update(Student student) {
        Student newStudent = findById(student.getId());
        updateData(newStudent, student);
        return studentRepository.save(newStudent);
    }

    @Transactional
    public void delete(Long id) {
        findById(id);
        try {
            studentRepository.deleteById(id);
        } catch (DataIntegrityException e) {
            throw new DataIntegrityException("Não é possível excluir este aluno!");
        }
    }

    private void updateData(Student newStudent, Student student) {
        newStudent.setGender(student.getGender());
        newStudent.setBirthDate(student.getBirthDate());
        newStudent.setAge(student.getAge());
        newStudent.setIntensity(student.getIntensity());
        newStudent.setObjective(student.getObjective());
        newStudent.setScheme(student.getScheme());
    }
    
}
