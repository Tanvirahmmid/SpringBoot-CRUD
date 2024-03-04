package com.example.demo.Student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class config {
@Bean
    CommandLineRunner commandLineRunner(Repo repo){
        return args -> {

        };

    }

}
