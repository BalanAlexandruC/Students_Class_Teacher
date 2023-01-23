package com.example.demo.mappers;

import com.example.demo.dto.CoursesDto;
import com.example.demo.models.Courses;
import org.mapstruct.Mapper;

@Mapper
public interface CoursesMapper {
    CoursesDto mapToDto(Courses courses);
}
