package com.example.demo.service;

import com.example.demo.ExcelExportUtil;
import com.example.demo.models.Student;
import com.example.demo.repositoryes.StudentRepository;
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
public class StudentService implements com.example.demo.service.interfaces.StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Cacheable(cacheNames = "students", key = "#id", condition="#p0!=null")
    public List<Student> getStudents() {return studentRepository.findAll();}
    @Cacheable(cacheNames = "students", key = "#id", condition="#p0!=null")
    public Student getStudent(Long id){ return  studentRepository.findById(id).get();}

    @CachePut(cacheNames = "students", key="#p0", condition="#p0!=null")
    public void addNewStudent(Student student) {
       Optional <Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if(studentOptional.isPresent()){
                throw  new IllegalStateException("email taken");
        }
        studentRepository.save(student);
    }

    @CacheEvict(cacheNames = "students",key="#p0", condition="#p0!=null")
    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if (!exists) {
            throw new IllegalStateException("Student " + studentId + " do not exists");
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new IllegalStateException(
                "Your student identified by " + studentId+" do not exists"));
        if (name != null &&
                name.length() > 0 &&
                !Objects.equals(student.getName(),name)){
            student.setName(name);
        }
        if (email !=null &&
        email.length()>0 &&
        !Objects.equals(student.getEmail(),email)){
           Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            if (studentOptional.isPresent()) {
                throw new IllegalStateException(("email taken"));
            }
            student.setEmail(email);
        }

    }
}
