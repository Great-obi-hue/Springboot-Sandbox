package com.great.securityapp.repository;

import com.great.securityapp.entity.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository
        extends MongoRepository<Student, String> {

}