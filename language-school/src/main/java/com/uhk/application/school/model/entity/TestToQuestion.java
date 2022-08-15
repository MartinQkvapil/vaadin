package com.uhk.application.school.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="test_to_question")
public class TestToQuestion implements Serializable {
    @Id
    @Column(nullable = false, updatable = false)
    private int idTestToQuestion;

    private int idTest;

    private int idQuestion;

    @Column(name="answ")
    private String answer;

    public TestToQuestion() {
    }

    public int getIdTestToQuestion() {
        return idTestToQuestion;
    }

    public void setIdTestToQuestion(int idTestToQuestion) {
        this.idTestToQuestion = idTestToQuestion;
    }

    public int getIdTest() {
        return idTest;
    }

    public void setIdTest(int idTest) {
        this.idTest = idTest;
    }

    public int getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(int idQuestion) {
        this.idQuestion = idQuestion;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
