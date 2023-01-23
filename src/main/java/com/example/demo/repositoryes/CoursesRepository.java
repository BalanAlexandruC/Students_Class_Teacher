package com.example.demo.repositoryes;

import com.example.demo.models.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CoursesRepository extends JpaRepository<Courses, Long> {
    Optional <Courses> findByCourseName(String courseName);

}
