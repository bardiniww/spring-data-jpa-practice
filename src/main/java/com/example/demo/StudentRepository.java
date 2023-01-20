package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

interface StudentRepository extends JpaRepository<Student, Long> {
}
