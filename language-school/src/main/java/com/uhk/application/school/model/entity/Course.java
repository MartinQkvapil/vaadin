package com.uhk.application.school.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="course")
public class Course extends AbstractEntity implements Serializable {
    @Id
    @Column(nullable = false, updatable = false)
    private int idCourse;

    private int points;

    private String native_lang;

    private String description;

    private int idTeachingLanguage;

    private int IdUser;

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

    public String getNative_lang() {
        return native_lang;
    }

    public void setNative_lang(String native_lang) {
        this.native_lang = native_lang;
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
        return IdUser;
    }

    public void setIdUser(int idUser) {
        IdUser = idUser;
    }
}