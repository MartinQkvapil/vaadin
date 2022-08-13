package com.uhk.application.school.model.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "teaching_lang")
public class TeachingLanguages implements Serializable {
    @Id
    @Column(name="id_teaching_lang", nullable = false, updatable = false)
    private String idTeachingLanguage;

    private String name;

    private String code;

    public TeachingLanguages() {}

    public String getIdTeachingLanguage() {
        return idTeachingLanguage;
    }

    public void setIdTeachingLanguage(String idTeachingLanguage) {
        this.idTeachingLanguage = idTeachingLanguage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
