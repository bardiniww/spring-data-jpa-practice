package com.example.demo;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "Enrolment")
@Table(name = "enrolment")
public class Enrolment {
    //the example of composite key which includes two fields (student_id, course_id)
    @EmbeddedId
    private EnrolmentId id;

    @ManyToOne
    @MapsId("studentId") //declares the field of EnrolmentId#studentId initialization
    @JoinColumn(
            name = "student_id",
            foreignKey = @ForeignKey(
                    name = "enrolment_student_id_fkey"
            )
    )
    private Student student;

    @ManyToOne
    @MapsId("courseId") //declares the field of EnrolmentId#courseId initialization
    @JoinColumn(
            name = "course_id",
            foreignKey = @ForeignKey(
                    name = "enrolment_course_id_fkey"
            )
    )
    private Course course;

    @Column(
            name = "created_at",
            nullable = false,
            columnDefinition = "TIMESTAMP WITHOUT TIME ZONE"
    )
    private LocalDateTime createdAt;

    public Enrolment(EnrolmentId id, Student student, Course course, LocalDateTime createdAt) {
        this.id = id;
        this.student = student;
        this.course = course;
        this.createdAt = createdAt;
    }

    public Enrolment(Student student, Course course, LocalDateTime createdAt) {
        this.student = student;
        this.course = course;
        this.createdAt = createdAt;
    }

    public Enrolment() {
    }

    public EnrolmentId getId() {
        return id;
    }

    public void setId(EnrolmentId id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Enrolment{" +
                "id=" + id +
                ", student=" + student +
                ", course=" + course +
                '}';
    }
}
