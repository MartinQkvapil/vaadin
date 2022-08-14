package com.uhk.application.school.controller;

import com.uhk.application.school.exception.QuestionException;
import com.uhk.application.school.model.entity.*;
import com.uhk.application.school.model.repository.*;
import com.uhk.application.school.model.validator.CourseValidator;
import com.uhk.application.school.model.validator.QuestionValidator;
import com.uhk.application.school.model.validator.UserValidator;
import com.uhk.application.school.model.validator.TestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Controller
@Transactional
public class MainControllerImpl implements LanguageSchool{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserValidator userValidator;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CourseValidator courseValidator;
    @Autowired
    private TeachingLanguagesRepository teachingLanguagesRepository;
    @Autowired
    private TestRepository testRepository;
    @Autowired
    private TestValidator testValidator;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private QuestionValidator questionValidator;

    @Override
    public User getUserByName(String name) {
        return userRepository.findByEmail(name);
    }

    @Override
    public List<TeachingLanguages> getAllLanguages() {
        return teachingLanguagesRepository.findAll();
    }

    @Override
    public void saveUser(User user) throws Exception {
        userValidator.validate(user);

        Optional<User> temp = userRepository.findById(user.getIdUser());
        if (temp.isPresent()) {
            user.setIdUser(temp.get().getIdUser());
        }

        userRepository.save(user);
    }

    @Override
    public void saveCourse(Course course) throws Exception {
        courseValidator.validate(course);
        courseRepository.save(course);
    }

    @Override
    public List<Test> getAllTestsByUserId(int userId) {
        return testRepository.findAllByUserId(userId);
    }

    @Override
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    @Override
    public void saveQuestion(Question question) throws Exception {
        questionValidator.validate(question);

        Optional<Question> temp = questionRepository.findById(question.getIdQuestion());
        if (temp.isPresent()) {
            question.setIdQuestion(temp.get().getIdQuestion());
        }
        
        questionRepository.save(question);
    }
    @Override
    public void removeQuestion(Question question) {
        questionRepository.delete(question);
    }
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
