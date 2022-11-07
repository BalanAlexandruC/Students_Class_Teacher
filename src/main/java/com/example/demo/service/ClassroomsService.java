package com.example.demo.service;

import com.example.demo.models.Classrooms;
import com.example.demo.repositoryes.ClassroomsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassroomsService {
    final private ClassroomsRepository classroomsRepository;

    @Autowired
    public ClassroomsService(ClassroomsRepository classroomsRepository) {
        this.classroomsRepository = classroomsRepository;
    }

    public List<Classrooms> getClassrooms() {
        return classroomsRepository.findAll();
    }

    public void addNewClassroom(Classrooms classrooms){
        classroomsRepository.save(classrooms);
    }

    public void deleteClassroom(Long classroomId){
        boolean exists = classroomsRepository.existsById(classroomId);
        if(!exists){
            throw new IllegalStateException("Classroom identified by " + classroomId+" is not existing");
            }
        classroomsRepository.deleteById(classroomId);
    }

}
