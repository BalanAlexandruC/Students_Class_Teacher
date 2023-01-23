package com.example.demo.service.interfaces;

import com.example.demo.models.Classrooms;

import java.util.List;

public interface ClassroomsService {
    public List<Classrooms> getClassrooms();
    public Classrooms getClassroomById(Long id);
    public void addNewClassroom(Classrooms classrooms);
    public void deleteClassroom(Long classroomId);

}
