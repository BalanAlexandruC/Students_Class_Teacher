package com.example.demo.service.interfaces;

import com.example.demo.models.Student;

import java.util.List;

public interface StudentService {

    public List<Student> getStudents();
    public Student getStudent(Long id);
    public void addNewStudent(Student student);
    public void deleteStudent(Long studentId);
    public void updateStudent(Long studentId, String name, String email);


}
