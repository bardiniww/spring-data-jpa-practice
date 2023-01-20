package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args -> {
            Student student2 = new Student();
            Student student = new Student(
                    "firstName",
                    "lastName",
                    13,
                    "email"
            );
            Student student1 = new Student(
                    "firstName1",
                    "lastName1",
                    14,
                    "email1"
            );
            studentRepository.save(student);
            studentRepository.save(student1);
        };
    }
}
