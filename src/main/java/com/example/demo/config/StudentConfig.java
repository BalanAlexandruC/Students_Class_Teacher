/*
package com.example.demo.config;

import com.example.demo.models.Student;
import com.example.demo.repositoryes.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

import static java.time.Month.JANUARY;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            Student marian = new Student(
                    "Marian",
                    "marianCelMare@yahoo.com",
                    LocalDate.of(2001, JANUARY, 5)
            );
            Student alex = new Student(
                    "Alex",
                    "Alex123@yahoo.com",
                    LocalDate.of(2001, JANUARY, 5)
            );
           repository.saveAll(List.of(alex,marian));

        };
    }
}

*/
