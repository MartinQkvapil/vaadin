package com.uhk.application.school.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="test_to_question")
public class TestToQuestion extends AbstractEntity implements Serializable {
    @Id
    @Column(nullable = false, updatable = false)
    private int idTestToQuestion;

    private int idTest;

    private int idQuestion;

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
}
