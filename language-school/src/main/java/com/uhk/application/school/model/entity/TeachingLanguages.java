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
    private int idTeachingLanguage;

    private String name;

    private String code;

    public TeachingLanguages() {}

    public int getIdTeachingLanguage() {
        return idTeachingLanguage;
    }

    public void setIdTeachingLanguage(int idTeachingLanguage) {
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

    @Override
    public int hashCode() {
        return idTeachingLanguage;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TeachingLanguages)) {
            return false;
        }
        TeachingLanguages other = (TeachingLanguages) obj;
        return idTeachingLanguage == other.idTeachingLanguage;
    }
}
