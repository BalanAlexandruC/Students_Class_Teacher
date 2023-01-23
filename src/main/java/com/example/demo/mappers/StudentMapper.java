package com.example.demo.mappers;

import com.example.demo.dto.StudentDto;
import com.example.demo.models.Student;
import org.mapstruct.Mapper;

@Mapper
public interface StudentMapper {
    StudentDto mapToDto(Student student);
}
