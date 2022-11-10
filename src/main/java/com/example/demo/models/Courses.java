package com.example.demo.models;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "Courses")
@Table(name = "courses")
public class Courses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinTable(
            name = "student_enrolled",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private Set<Student> enrolledStudents = new HashSet<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher_id", referencedColumnName = "id")
    private Teacher teacher;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "classroom_id", referencedColumnName = "id")
    private Classrooms classroom;


    private String courseName;
    private Long duration;

    public Long getId() {
        return id;
    }

    public Set<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public String getCourseName() {
        return courseName;
    }

    public Long getDuration() {
        return duration;
    }

    public Classrooms getClassroom() {return classroom;}

    public void setId(Long id) {
        this.id = id;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public Courses() {
    }

    public Courses(Long id, String courseName, Long duration) {
        this.id = id;
        this.courseName = courseName;
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Courses{" +
                "id=" + id +
                ", enrolledStudents=" + enrolledStudents +
                ", teacher=" + teacher +
                ", courseName='" + courseName + '\'' +
                ", duration=" + duration +
                '}';
    }

    public void enrollStudent(Student student) {
        enrolledStudents.add(student);
    }

    public void assignTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public void assignClassroom(Classrooms classrooms){
        this.classroom = classrooms;
        classrooms.setAvailability(Boolean.FALSE);
    }
}
