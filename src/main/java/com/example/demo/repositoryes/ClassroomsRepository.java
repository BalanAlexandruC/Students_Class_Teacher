package com.example.demo.repositoryes;

import com.example.demo.models.Classrooms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClassroomsRepository extends JpaRepository <Classrooms,Long> {
    Optional<Classrooms> findByName(String name);
}
