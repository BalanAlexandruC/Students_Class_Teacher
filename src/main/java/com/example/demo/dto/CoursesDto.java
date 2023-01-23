package com.example.demo.dto;

import com.example.demo.models.Classrooms;
import com.example.demo.models.Student;
import com.example.demo.models.Teacher;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@Getter
public class CoursesDto {

    private final Set<Student> enrolledStudents = new HashSet<>();
    private final Teacher teacher;
    private final Classrooms classrooms;
    private String courseName;

}
