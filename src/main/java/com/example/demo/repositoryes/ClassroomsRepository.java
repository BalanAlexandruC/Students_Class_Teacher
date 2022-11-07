package com.example.demo.repositoryes;

import com.example.demo.models.Classrooms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassroomsRepository extends JpaRepository <Classrooms,Long> {
}
