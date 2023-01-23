package com.example.demo.service.interfaces;

import com.example.demo.models.Teacher;

import java.util.List;

public interface TeacherService {
    public List<Teacher> getTeachers();
    public Teacher getTeacher(Long id);
    public void addNewTeacher(Teacher teacher);
    public void deleteTeacher(Long teacherId);
    public void updateTeacher(Long teacherId, String name);
    public void likeTeacher(Long teacherId);
}
