package com.example.demo.dto;

import com.example.demo.models.Courses;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;
@AllArgsConstructor
@Getter
public class TeacherDto {
    private final Set<Courses> courses= new HashSet<>();
    private final String name;
    private final Long likes;
}
