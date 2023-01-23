package com.example.demo.dto;

import com.example.demo.models.Courses;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@Getter
public class StudentDto {
    private final Long id;
    private final String name;
    private final String email;
    private final Integer age;
    private final Set<Courses> coursesSet= new HashSet<>();
}
