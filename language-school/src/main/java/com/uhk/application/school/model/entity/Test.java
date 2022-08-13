package com.uhk.application.school.model.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="test")
public class Test extends AbstractEntity implements Serializable {
    @Id
    @Column(nullable = false, updatable = false)
    private int idTest;

    private int questionCount;

    @Column(name="user_id_user")
    private int userId;

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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
