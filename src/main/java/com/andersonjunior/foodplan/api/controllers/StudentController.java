package com.andersonjunior.foodplan.api.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.andersonjunior.foodplan.domain.models.Student;
import com.andersonjunior.foodplan.service.services.StudentService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/students")
public class StudentController {
    
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @ApiOperation(value = "Lista um aluno por ID")
    @GetMapping(value = "/{id}")
    public ResponseEntity<Student> findById(@PathVariable Long id) {
        Student student = studentService.getStudentById(id);
        return ResponseEntity.ok().body(student);
    }

    @ApiOperation(value = "Lista todos os aluno")
    @GetMapping
    public ResponseEntity<List<Student>> findAll(
        @RequestParam(name = "page", required = true, defaultValue = "0") Integer page,
        @RequestParam(name = "size", required = true, defaultValue = "50") Integer size) {
            List<Student> students = studentService.getStudents(page, size);
            return ResponseEntity.ok().body(students);
    }

    @ApiOperation(value = "Insere um novo aluno")
    @PostMapping
    public ResponseEntity<String> insert(@Valid @RequestBody Student student) {
        studentService.saveStudent(student);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(student.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @ApiOperation(value = "Edita um aluno")
    @PutMapping(value = "/{id}")
    public ResponseEntity<String> update(@Valid @RequestBody Student student, @PathVariable Long id) {
        studentService.saveStudent(student);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Exclui um aluno")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@Valid @PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

}
