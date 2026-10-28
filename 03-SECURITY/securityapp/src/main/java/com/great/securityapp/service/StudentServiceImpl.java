package com.great.securityapp.service;

import com.great.securityapp.dto.StudentRequest;
import com.great.securityapp.exception.ResourceNotFoundException;
import com.great.securityapp.entity.Student;
import com.great.securityapp.repository.StudentRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository repository;

    @Override
    public Student createStudent(StudentRequest request) {

        Student student = Student.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .age(request.getAge())
                .department(request.getDepartment())
                .cgpa(request.getCgpa())
                .build();

        return repository.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    @Override
    public Student getStudentById(String id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student not found"));
    }

    @Override
    public Student updateStudent(String id, StudentRequest request) {

        Student student = getStudentById(id);

        student.setFirstName(request.getFirstName());
        student.setLastName(request.getLastName());
        student.setAge(request.getAge());
        student.setDepartment(request.getDepartment());
        student.setCgpa(request.getCgpa());

        return repository.save(student);
    }

    @Override
    public void deleteStudent(String id) {

        Student student = getStudentById(id);

        repository.delete(student);
    }

}
