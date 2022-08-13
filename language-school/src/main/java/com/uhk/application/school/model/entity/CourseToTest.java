package com.uhk.application.school.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="course_to_test")
public class CourseToTest implements Serializable {

    @Id
    @Column(nullable = false, updatable = false)
    private int idCourseToTest;

    private int idCourse;

    private int idTest;

    public CourseToTest() {}

    public int getIdCourseToTest() {
        return idCourseToTest;
    }

    public void setIdCourseToTest(int idCourseToTest) {
        this.idCourseToTest = idCourseToTest;
    }

    public int getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(int idCourse) {
        this.idCourse = idCourse;
    }

    public int getIdTest() {
        return idTest;
    }

    public void setIdTest(int idTest) {
        this.idTest = idTest;
    }
}