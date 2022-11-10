package com.example.demo.service;

import com.example.demo.models.Teacher;
import com.example.demo.repositoryes.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TeacherService {
    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public List<Teacher> getTeacher() {
        return teacherRepository.findAll();
    }

    public void addNewTeacher(Teacher teacher) {

        Optional<Teacher> teacherOptional = teacherRepository.findByTeacher(teacher.getTeacher());
        if (teacherOptional.isPresent()) {
            throw new IllegalStateException("Already existing teacher");
        }
            teacherRepository.save(teacher);
    }

    public void deleteTeacher(Long teacherId) {
        boolean exists = teacherRepository.existsById(teacherId);
        if (!exists) {
            throw new IllegalStateException("Teacher identified by " + teacherId + " do not exists");
        }
        teacherRepository.deleteById(teacherId);
    }

    @Transactional
    public void updateTeacher(Long teacherId, String name) {
        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow(() -> new IllegalStateException(
                "Teacher identified by " + teacherId+" do not exists"));
        if (name != null &&
                name.length() > 0 &&
                !Objects.equals(teacher.getTeacher(),name)){
            teacher.setTeacher(name);
        }

    }
}
