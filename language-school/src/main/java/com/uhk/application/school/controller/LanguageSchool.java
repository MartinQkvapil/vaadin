package com.uhk.application.school.controller;

import com.uhk.application.school.model.entity.*;

import java.util.List;

public interface LanguageSchool {
    User getUserByName(String name);

    List<TeachingLanguages> getAllLanguages();

    void saveUser(User user) throws Exception;

    void saveCourse(Course course) throws Exception;

    List<Test> getAllTestsByUserId(int idUser);

    List<Question> getAllQuestions();

    void saveQuestion(Question question) throws Exception;

    void removeQuestion(Question question);

    List<User> getAllUsers();

    List<Question> getAllQuestionsByTestId(int idTest);

    Test saveTest(Test test) throws Exception;

    List<Test>  getAllTests();

    void removeTest(Test currentTest);

    void removeTestToQuestion(Question currentQuestion, Test currentTest);

    void saveTestToQuestion(TestToQuestion testToQuestion);

    List<Test> getAllTestsByLanguage(int idTeachingLanguage);
}
