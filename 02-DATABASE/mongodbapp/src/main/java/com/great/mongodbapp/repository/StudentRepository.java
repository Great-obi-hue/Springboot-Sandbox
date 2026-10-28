package com.great.mongodbapp.repository;

import com.great.mongodbapp.entity.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository
        extends MongoRepository<Student, String> {

}