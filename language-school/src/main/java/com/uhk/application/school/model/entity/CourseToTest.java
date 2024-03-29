package com.uhk.application.school.model.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="course_to_test")
public class CourseToTest implements Serializable {

    @Id
    @Column(nullable = false, name="id_course_to_test")
    private int idCourseToTest;
    @Column(name="id_course")
    private int idCourse;
    @Column(name="id_test")
    private int idTest;

    @Column(name="done")
    private int done;

    @Column(name="wrong_answers")
    private int wrongAnswers;

    @Column(name="correct_answers")
    private int correctAnswers;

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(int correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public int getWrongAnswers() {
        return wrongAnswers;
    }

    public void setWrongAnswers(int wrongAnswers) {
        this.wrongAnswers = wrongAnswers;
    }

    public int getDone() {
        return done;
    }

    public void setDone(int done) {
        this.done = done;
    }

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

    @Override
    public String toString() {
        return "CourseToTest{" +
                "idCourseToTest=" + idCourseToTest +
                ", idCourse=" + idCourse +
                ", idTest=" + idTest +
                ", done=" + done +
                '}';
    }
}