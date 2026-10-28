package com.great.securityapp.service;

import com.great.securityapp.dto.StudentRequest;
import com.great.securityapp.entity.Student;

import java.util.List;

public interface StudentService {

    Student createStudent(StudentRequest request);

    List<Student> getAllStudents();

    Student getStudentById(String id);

    Student updateStudent(String id, StudentRequest request);

    void deleteStudent(String id);

}
