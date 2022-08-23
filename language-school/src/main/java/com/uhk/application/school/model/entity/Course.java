package com.uhk.application.school.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="course")
public class Course implements Serializable {
    @Id
    @Column(nullable = false, name = "id_course")
    private int idCourse;

    private int points;

    private String nativeLang;

    private String description;
    @Column(name = "id_teaching_lang")
    private int idTeachingLanguage;

    private int idUser;

    public Course() {}

    public int getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(int idCourse) {
        this.idCourse = idCourse;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getNativeLang() {
        return nativeLang;
    }

    public void setNativeLang(String native_lang) {
        this.nativeLang = native_lang;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIdTeachingLanguage() {
        return idTeachingLanguage;
    }

    public void setIdTeachingLanguage(int idTeachingLanguage) {
        this.idTeachingLanguage = idTeachingLanguage;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Override
    public String toString() {
        return "Course{" +
                "idCourse=" + idCourse +
                ", points=" + points +
                ", native_lang='" + nativeLang + '\'' +
                ", description='" + description + '\'' +
                ", idTeachingLanguage=" + idTeachingLanguage +
                ", idUser=" + idUser +
                '}';
    }
}