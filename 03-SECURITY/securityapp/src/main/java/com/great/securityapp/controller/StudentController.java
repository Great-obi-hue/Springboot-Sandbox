package com.great.securityapp.controller;

import com.great.securityapp.dto.StudentRequest;
import com.great.securityapp.entity.Student;
import com.great.securityapp.service.StudentService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService service;

    @PostMapping
    public Student createStudent(
            @Valid @RequestBody StudentRequest request){

        return service.createStudent(request);
    }

    @GetMapping
    public List<Student> getStudents(){

        return service.getAllStudents();
    }

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable String id){

        return service.getStudentById(id);
    }

    @PutMapping("/{id}")
    public Student updateStudent(
            @PathVariable String id,
            @Valid @RequestBody StudentRequest request){

        return service.updateStudent(id, request);
    }

    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable String id){

        service.deleteStudent(id);

        return "Student deleted successfully";
    }

}
