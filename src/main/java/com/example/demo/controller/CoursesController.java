package com.example.demo.controller;


import com.example.demo.models.Courses;
import com.example.demo.service.CoursesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/classes")
public class CoursesController {

    private final CoursesService coursesService;

    @Autowired
    public CoursesController(CoursesService coursesService) {
        this.coursesService = coursesService;
    }

    @GetMapping("/findAllCourses")
    public List<Courses> getCourses(){return coursesService.getCourses();}

    @PostMapping("/registerNewCourse")
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



    @PutMapping("/{studentId}/students/{courseId}")
    public void enrolledStudentToCourses(
            @PathVariable("courseId") Long courseId,
            @PathVariable("studentId") Long studentId
    ){
        coursesService.enrollStudentToCourse(courseId,studentId);
    }
    @PutMapping("/{teacherId}/teacher/{courseId}")
    public void assignTeacherToCourse(
            @PathVariable("courseId") Long courseId,
            @PathVariable("teacherId") Long teacherId
    ){
        coursesService.assignTeacherToCourse(courseId,teacherId);
    }
    @PutMapping("/{classroomId}/classroom/{courseId}")
    public void assignClassroomToCourse(
            @PathVariable("classroomId") Long classroomId,
            @PathVariable("courseId") Long courseId
    ){
        coursesService.assignClassroomToCourse(classroomId,courseId);
    }




}
