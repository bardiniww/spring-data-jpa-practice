package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

interface StudentRepository extends JpaRepository<Student, Long> {
    @Query(
            "SELECT s FROM Student s WHERE s.email = ?1"
    )
    Optional<Student> findStudentByEmail(String email);


    @Query(
            "SELECT s FROM Student s " +
                    "WHERE s.firstName = :firstName " +
                    "AND s.age >= :age"
    )
    List<Student> findStudentsByFirstNameEqualsAndAgeIsGreaterThanEqual(
            @Param("firstName") String firstName,
            @Param("age") Integer age
    );

    //not recommended to use native, because of in case if database provider changes, a query will not work
    @Query(
            value = "SELECT * FROM student s " +
                    "WHERE s.firstName = ?1 " +
                    "AND s.age >= ?2",
            nativeQuery = true
    )
    List<Student> findStudentsByFirstNameEqualsAndAgeIsGreaterThanEqualNative(String firstName, Integer age);
}
