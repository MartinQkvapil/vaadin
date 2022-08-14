package com.uhk.application.school.model.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="test")
public class Test implements Serializable {
    @Id
    @Column(nullable = false, updatable = false, name="id_test")
    private int idTest;

    @Column(name="question_count")
    private int questionCount;

    public Test() {}

    public int getIdTest() {
        return idTest;
    }

    public void setIdTest(int idTest) {
        this.idTest = idTest;
    }

    public int getQuestionCount() {
        return questionCount;
    }

    public void setQuestionCount(int questionCount) {
        this.questionCount = questionCount;
    }
}
