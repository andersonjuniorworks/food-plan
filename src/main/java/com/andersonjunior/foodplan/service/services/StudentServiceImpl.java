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

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    
    @Override
    public Student getStudentById(Long id) {
        log.info("Listando aluno por ID");
        Optional<Student> student = studentRepository.findById(id);
        return student.orElseThrow(() -> new ObjectNotFoundException("Registro não encontrado na base de dados"));
    }

    @Override
    public List<Student> getStudents(Integer page, Integer pageSize) {
        log.info("Listando todos os alunos");
        Pageable pageable = PageRequest.of(page, pageSize);
        return studentRepository.findAll(pageable).getContent();
    }

    @Override
    public Student saveStudent(Student student) {
        log.info("Salvando um novo aluno {} no banco de dados", student.getAccess().getName());
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Long id) {
        getStudentById(id);
        try {
            log.info("Excluindo o aluno {}", getStudentById(id).getAccess().getName());
            studentRepository.deleteById(id);
        } catch (DataIntegrityException e) {
            throw new DataIntegrityException("Não é possível excluir este aluno!");
        }
    }
    
}
