package com.example.demo.mappers;

import com.example.demo.dto.TeacherDto;
import com.example.demo.models.Teacher;
import org.mapstruct.Mapper;

@Mapper
public interface TeacherMapper {
    TeacherDto mapToDto(Teacher teacher);
}
