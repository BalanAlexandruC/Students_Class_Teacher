/*
package com.example.demo.config;

import com.example.demo.models.Courses;
import com.example.demo.repositoryes.CoursesRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
@Configuration
public class CoursesConfig {

    @Bean
    CommandLineRunner commandLineRunner(CoursesRepository repository) {
        return args -> {
            Courses mathematics = new Courses(

                    "Mathematics",
                    "Bilal",
                    0L,
                    12L
            );
            Courses physics = new Courses(

                    "Physics",
                    "Bilal",
                    0L,
                    6L
            );
            repository.saveAll(List.of(mathematics,physics));

        };

    }
}
*/
