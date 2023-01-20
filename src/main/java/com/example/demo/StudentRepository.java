package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

interface StudentRepository extends JpaRepository<Student, Long> {
    @Query(
            "SELECT s FROM Student s WHERE s.email = ?1"
    )
    Optional<Student> findStudentByEmail(String email);


    @Query(
            "SELECT s FROM Student s " +
                    "WHERE s.firstName = ?1 " +
                    "AND s.age >= ?2"
    )
    List<Student> findStudentsByFirstNameEqualsAndAgeIsGreaterThanEqual(String firstName, Integer age);

    @Query(
            value = "SELECT * FROM student s " +
                    "WHERE s.firstName = ?1 " +
                    "AND s.age >= ?2",
            nativeQuery = true
    )
    List<Student> findStudentsByFirstNameEqualsAndAgeIsGreaterThanEqualNative(String firstName, Integer age);
}
