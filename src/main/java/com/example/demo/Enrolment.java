package com.example.demo;

import javax.persistence.*;

@Entity(name = "Enrolment")
@Table(name = "enrolment")
public class Enrolment {
    //the example of composite key which includes two fields (student_id, course_id)
    @EmbeddedId
    private EnrolmentId id;

    @ManyToOne
    @MapsId("studentId") //declares the field of EnrolmentId#studentId initialization
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @MapsId("courseId") //declares the field of EnrolmentId#courseId initialization
    @JoinColumn(name = "course_id")
    private Course course;
}
