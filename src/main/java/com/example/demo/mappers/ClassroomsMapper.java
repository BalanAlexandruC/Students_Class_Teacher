package com.example.demo.mappers;

import com.example.demo.dto.ClassroomsDto;
import com.example.demo.models.Classrooms;
import org.mapstruct.Mapper;

@Mapper
public interface ClassroomsMapper {
    ClassroomsDto mapToDto(Classrooms classrooms);
    Classrooms dtoToMap(ClassroomsDto classroomsDto);
}
