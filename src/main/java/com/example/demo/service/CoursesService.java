package com.example.demo.service;

import com.example.demo.models.Classrooms;
import com.example.demo.models.Courses;
import com.example.demo.models.Student;
import com.example.demo.models.Teacher;
import com.example.demo.repositoryes.ClassroomsRepository;
import com.example.demo.repositoryes.CoursesRepository;
import com.example.demo.repositoryes.StudentRepository;
import com.example.demo.repositoryes.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CoursesService implements com.example.demo.service.interfaces.CoursesService {

    private final CoursesRepository coursesRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final ClassroomsRepository classroomsRepository;

    @Autowired
    public CoursesService(CoursesRepository coursesRepository, StudentRepository studentRepository, TeacherRepository teacherRepository, ClassroomsRepository classroomsRepository) {
        this.coursesRepository = coursesRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
        this.classroomsRepository = classroomsRepository;
    }

    @Cacheable(cacheNames = "courses", key = "#id", condition="#p0!=null")
    public List<Courses> getCourses() {
        return coursesRepository.findAll();
    }
    @Cacheable(cacheNames = "courses", key = "#id", condition="#p0!=null")
    public Courses getCourse(Long id) {return coursesRepository.findById(id).get();}

    @CachePut(cacheNames = "courses", key="#p0", condition="#p0!=null")
    public void addNewCourse(Courses courses) {
       Optional <Courses> coursesOptional = coursesRepository.findByCourseName(courses.getCourseName());
        if (coursesOptional.isPresent()) {
            throw new IllegalStateException("Already existing course");
        }
        coursesRepository.save(courses);
    }

    @CacheEvict(cacheNames = "courses",key="#p0", condition="#p0!=null")
    public void deleteCourse(Long courseId) {
        boolean exists = coursesRepository.existsById(courseId);
        if (!exists) {
            throw new IllegalStateException("Your course identified by:" + courseId + " do not exist");
        }
        coursesRepository.deleteById(courseId);
    }
    @Transactional
    public void updateCourse(Long courseId, String courseName, Long duration) {
        Courses courses = coursesRepository.findById(courseId).orElseThrow(() -> new IllegalStateException(
                "Your student identified by " + courseId + " do not exists"));
        if (courseName != null &&
                courseName.length() > 0 &&
                !Objects.equals(courses.getCourseName(), courseName)) {
            courses.setCourseName(courseName);
        }
        if (duration != null &&
                duration > 0)
            courses.setDuration(duration);


    }

    @Transactional
    public void enrollStudentToCourse(Long courseId, Long studentId) {
        boolean existsCourse = coursesRepository.existsById(courseId);
        boolean existsStudent = studentRepository.existsById(studentId);
        if (!existsCourse) {
            throw new IllegalStateException("nu exista cursul dumneavoastra");
        }
        if (!existsStudent) {
            throw new IllegalStateException("nu exista studnetul in baza de date");
        }
        Courses courses = coursesRepository.findById(courseId).get();
        Student student = studentRepository.findById(studentId).get();
        courses.enrollStudent(student);
        coursesRepository.save(courses);
    }
    @Transactional
    public void assignTeacherToCourse(Long courseId, Long teacherId) {
        Courses courses = coursesRepository.findById(courseId).get();
        Teacher teacher = teacherRepository.findById(teacherId).get();
        courses.assignTeacher(teacher);
        coursesRepository.save(courses);
    }
    @Transactional
    public void assignClassroomToCourse(Long classroomId, Long courseId) {
        Classrooms classrooms = classroomsRepository.findById(classroomId).get();
        Courses courses = coursesRepository.findById(courseId).get();
        if (classrooms.getAvailability()==Boolean.FALSE) {
            throw new IllegalStateException("Your class is not available");
        }
        courses.assignClassroom(classrooms);
        coursesRepository.save(courses);
    }


}

