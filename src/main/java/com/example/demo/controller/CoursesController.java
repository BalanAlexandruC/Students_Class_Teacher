package com.example.demo.controller;


import com.example.demo.models.Courses;
import com.example.demo.service.CoursesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/course")
public class CoursesController {

    private final CoursesService coursesService;

    @Autowired
    public CoursesController(CoursesService coursesService) {
        this.coursesService = coursesService;
    }

    @GetMapping("/get")
    public List<Courses> getCourses(){return coursesService.getCourses();}
    @GetMapping("/get/{courseId}")
    public Courses getCourse(@PathVariable Long courseId){return coursesService.getCourse(courseId);}

    @PostMapping("/register")
    public void registerNewCourse(@RequestBody  Courses courses){
        coursesService.addNewCourse(courses);
    }
    @DeleteMapping(path = "{courseId}")
    public void deleteCourse(@PathVariable("courseId") Long courseId){coursesService.deleteCourse(courseId);}

    @PutMapping(path = "{courseId}")
    public void updateCourse(@PathVariable("courseId") Long courseId,
                              @RequestParam(required = false) String courseName,
                              @RequestParam(required = false) Long duration) {
        coursesService.updateCourse(courseId, courseName, duration);
    }



    @PutMapping("/{courseId}/student/{studentId}")
    public void enrolledStudentToCourses(
            @PathVariable("courseId") Long courseId,
            @PathVariable("studentId") Long studentId
    ){
        coursesService.enrollStudentToCourse(courseId,studentId);
    }
    @PutMapping("/{courseId}/teacher/{teacherId}")
    public void assignTeacherToCourse(
            @PathVariable("courseId") Long courseId,
            @PathVariable("teacherId") Long teacherId
    ){
        coursesService.assignTeacherToCourse(courseId,teacherId);
    }
    @PutMapping("/{courseId}/classroom/{classroomId}")
    public void assignClassroomToCourse(
            @PathVariable("classroomId") Long classroomId,
            @PathVariable("courseId") Long courseId
    ){
        coursesService.assignClassroomToCourse(classroomId,courseId);
    }




}
