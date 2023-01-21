package com.example.demo;

import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository, StudentIdCardRepository studentIdCardRepository) {
        return args -> {
//            generateRandomStudents(studentRepository);
//            findAllStudentsWithSort(studentRepository);
//            PageRequest pageRequest = PageRequest.of(
//                    0,
//                    10,
//                    Sort.by(Sort.Direction.ASC, "firstName"));
//            Page<Student> page = studentRepository.findAll(pageRequest);
//            System.out.println(page);

            Faker faker = new Faker();
            Name name = faker.name();
            Student student = new Student(
                    name.firstName(),
                    name.lastName(),
                    faker.number().numberBetween(17, 89),
                    name.username() + "@email.com"
            );

            StudentIdCard studentIdCard = new StudentIdCard(
                    "123",
                    student
            );
            studentIdCardRepository.save(studentIdCard);

            studentRepository.findById(1L).ifPresent(System.out::println);

            studentIdCardRepository.findById(1L).ifPresent(System.out::println);

        };
    }

    private static void findAllStudentsWithSort(StudentRepository studentRepository) {
        Sort sort = Sort.by(Sort.Direction.ASC, "firstName");
        studentRepository.findAll(sort).forEach(st -> System.out.println(st.getFirstName()));
    }

    private void generateRandomStudents(StudentRepository studentRepository) {
        Faker faker = new Faker();
        for (int i = 0; i < 20; i++) {
            Name name = faker.name();
            studentRepository.save(
                    new Student(
                            name.firstName(),
                            name.lastName(),
                            faker.number().numberBetween(17, 89),
                            name.username() + "@email.com"
                    )
            );
        }
    }
}
