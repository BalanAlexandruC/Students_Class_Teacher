package com.example.demo.controller;

import com.example.demo.models.Teacher;
import com.example.demo.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/teacher")
public class TeacherController {
    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }
    @GetMapping("/get")
    public List<Teacher> getTeacher(){return teacherService.getTeacher();}
    @PostMapping("/register")
    public void registerTeacher(@RequestBody Teacher teacher){teacherService.addNewTeacher(teacher);}
    @DeleteMapping(path = "{teacherId}")
    public void deleteTeacher(@PathVariable("teacherId") Long teacherId){teacherService.deleteTeacher(teacherId);}

    @PutMapping(path = "{teacherId}")
    public void updateStudent(@PathVariable("teacherId") Long teacherId,
                              @RequestParam(required = false) String name) {
        teacherService.updateTeacher(teacherId, name);
    }
}
