package com.example.demo.controller;

import com.example.demo.models.Classrooms;
import com.example.demo.service.ClassroomsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/classrooms")
public class ClassroomsController {
    private final ClassroomsService classroomsService;

    @Autowired
    public ClassroomsController(ClassroomsService classroomsService) {
        this.classroomsService = classroomsService;
    }

    @GetMapping("/findAllClassrooms")
    public List<Classrooms> getClassrooms(){return classroomsService.getClassrooms();}

    @PostMapping("/registerNewClassrooms")
    public void registerNewCourse(@RequestBody Classrooms classrooms){
        classroomsService.addNewClassroom(classrooms);
    }

    @DeleteMapping(path = "{classroomId}")
    public void deleteCourse(@PathVariable("classroomId") Long classroomId){classroomsService.deleteClassroom(classroomId);}
}
