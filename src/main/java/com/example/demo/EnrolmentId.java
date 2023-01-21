package com.example.demo;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * It's an example how to make a composite id class for another parent entity
 */
@Embeddable //means that this class will be injected as part of another entity
public class EnrolmentId implements Serializable {
    @Column(name = "student_id")
    private Long studentId;

    @Column(name = "course_id")
    private Long courseId;

    public EnrolmentId(Long studentId, Long courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
    }

    public EnrolmentId() {
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        EnrolmentId that = (EnrolmentId) o;

        return new EqualsBuilder().append(studentId, that.studentId).append(courseId, that.courseId).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(studentId).append(courseId).toHashCode();
    }

    @Override
    public String toString() {
        return "EnrolmentId{" +
                "studentId=" + studentId +
                ", courseId=" + courseId +
                '}';
    }
}
