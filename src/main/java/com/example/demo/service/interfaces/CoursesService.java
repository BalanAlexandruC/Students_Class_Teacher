package com.example.demo.service.interfaces;

import com.example.demo.models.Courses;

import java.util.List;

public interface CoursesService {
    public List<Courses> getCourses();
    public Courses getCourse(Long id);
    public void addNewCourse(Courses courses);
    public void deleteCourse(Long courseId);
    public void updateCourse(Long courseId, String courseName, Long duration);
    public void enrollStudentToCourse(Long courseId, Long studentId);
    public void assignTeacherToCourse(Long courseId, Long teacherId);
    public void assignClassroomToCourse(Long classroomId, Long courseId);
}
