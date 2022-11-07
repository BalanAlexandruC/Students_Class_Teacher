package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity(name = "Classrooms")
@Table(name = "classrooms")
public class Classrooms {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_classrooms")
    private Long id;
    @JsonIgnore
    @OneToOne(mappedBy = "classroom")
    private Courses courses;

    private String name;

    private Long capacity;

    private Boolean availability;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getCapacity() {
        return capacity;
    }

    public Boolean getAvailability() {
        return availability;
    }

    public Courses getCourses() {return courses;}

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCapacity(Long capacity) {
        this.capacity = capacity;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }

    public Classrooms() {
    }

    public Classrooms(Long id, String name, Long capacity, Boolean availability) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.availability = availability;
    }

    @Override
    public String toString() {
        return "Classrooms{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", capacity=" + capacity +
                ", availability=" + availability +
                '}';
    }
}
