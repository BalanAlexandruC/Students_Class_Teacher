package com.example.demo.repositoryes;

import com.example.demo.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    //@Query("SELECT s FROM student s WHERE s.email = ?1")
    Optional<Student> findStudentByEmail(String email);

}
