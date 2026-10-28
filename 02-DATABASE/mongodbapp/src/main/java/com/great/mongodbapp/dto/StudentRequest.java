package com.great.mongodbapp.dto;

import jakarta.validation.constraints.*;

import lombok.Data;

@Data
public class StudentRequest {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @Min(15)
    @Max(100)
    private Integer age;

    @NotBlank
    private String department;

    @DecimalMin("0.0")
    @DecimalMax("5.0")
    private Double cgpa;

}