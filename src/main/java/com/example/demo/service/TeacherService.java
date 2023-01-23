package com.example.demo.service;

import com.example.demo.ExcelExportUtil;
import com.example.demo.models.Student;
import com.example.demo.models.Teacher;
import com.example.demo.repositoryes.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TeacherService implements com.example.demo.service.interfaces.TeacherService {
    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Cacheable(cacheNames = "teachers", key = "#id", condition="#p0!=null")
    public List<Teacher> getTeachers() {
        return teacherRepository.findAll();
    }
    @Cacheable(cacheNames = "teachers", key = "#id", condition="#p0!=null")
    public Teacher getTeacher(Long id) {
        return teacherRepository.findById(id).get();
    }
    public List<Teacher> exportToExcel(HttpServletResponse response) throws IOException {
        List<Teacher> teacher=teacherRepository.findAll();
        ExcelExportUtil excelExportUtil=new ExcelExportUtil(teacher);
        excelExportUtil.exportDataToExcel(response);
        return teacher;
    }

    @CachePut(cacheNames = "teachers", key="#p0", condition="#p0!=null")
    public void addNewTeacher(Teacher teacher) {
        Optional <Teacher> teacherOptional = teacherRepository.findTeacherByName(teacher.getName());
        teacherOptional.ifPresent(value -> {
            throw new IllegalStateException("Already existing teacher");
        });
            teacherRepository.save(teacher);
    }

    @CacheEvict(cacheNames = "teachers",key="#p0", condition="#p0!=null")
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
                !Objects.equals(teacher.getName(),name)){
            teacher.setName(name);
        }

    }

    public void likeTeacher(Long teacherId) {
        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow(() -> new IllegalStateException(
                "Teacher identified by " + teacherId+" do not exists"));
        teacher.setLikes(teacher.getLikes()+1);
        teacherRepository.save(teacher);
    }
}
