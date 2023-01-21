package com.example.demo;

import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(
            StudentRepository studentRepository
    ) {
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
            Book book1 = new Book(
                    "BookName1",
                    LocalDateTime.now()
            );

            Book book2 = new Book(
                    "BookName2",
                    LocalDateTime.now()
            );

            Book book3 = new Book(
                    "BookName3",
                    LocalDateTime.now()
            );

            student.addBook(book1);
            student.addBook(book2);
            student.addBook(book3);

            StudentIdCard studentIdCard = new StudentIdCard(
                    "123",
                    student
            );

            student.setStudentIdCard(studentIdCard);

            student.enrolToCourse(new Course("Data Science", "IT"));
            student.enrolToCourse(new Course("Java", "IT"));

            studentRepository.save(student);

            studentRepository.findById(1L).ifPresent(s -> {
                System.out.println("fetch book lazy...");
                List<Book> books = student.getBooks();
                books.forEach(b -> {
                    System.out.println(s.getFirstName() + " borrowed " + b.getBookName());
                });
            });
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
