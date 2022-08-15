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

    @Column(name="test_name")
    private String testName;

    @Column(name="id_teaching_lang")
    private int testLanguageId;

    public int getTestLanguage() {
        return testLanguageId;
    }

    public void setTestLanguage(int languageId) {
        this.testLanguageId = languageId;
    }

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

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    @Override
    public String toString() {
        return "Test{" +
                "idTest=" + idTest +
                ", questionCount=" + questionCount +
                '}';
    }
}
