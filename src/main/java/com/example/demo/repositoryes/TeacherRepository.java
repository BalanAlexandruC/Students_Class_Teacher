package com.example.demo.repositoryes;

import com.example.demo.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository <Teacher,Long> {
    Optional<Teacher> findTeacherByName(String name);
}
