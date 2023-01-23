package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
    @OneToMany(mappedBy = "teacher", fetch = FetchType.LAZY)
    private Set<Courses> courses= new HashSet<>();

    private String name;
    private Long likes;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setLikes(Long likes) {
        this.likes = likes;
    }


    public Teacher(Long id, String name, Long likes) {
        this.id = id;
        this.name = name;
        this.likes = likes;
    }

    public Teacher() {
    }

    public Teacher(String name, Long likes) {
        this.name = name;
        this.likes = likes;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", teacher='" + name + '\'' +
                ", likes=" + likes +
                '}';
    }
}
