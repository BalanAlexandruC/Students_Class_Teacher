package com.example.demo.models;

import com.example.demo.repositoryes.TeacherRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name= "Teacher")
@Table(name="teachers")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @OneToMany(mappedBy = "teacher")
    private Set<Courses> courses= new HashSet<>();

    private String teacher;
    private Long likes;

    public Long getId() {
        return id;
    }

    public String getTeacher() {
        return teacher;
    }

    public Long getLikes() {
        return likes;
    }

    public Set<Courses> getCourses() {
        return courses;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public void setLikes(Long likes) {
        this.likes = likes;
    }


    public Teacher(Long id, String teacher, Long likes) {
        this.id = id;
        this.teacher = teacher;
        this.likes = likes;
    }

    public Teacher() {
    }

    public Teacher(String teacher, Long likes) {
        this.teacher = teacher;
        this.likes = likes;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", teacher='" + teacher + '\'' +
                ", likes=" + likes +
                '}';
    }
}
