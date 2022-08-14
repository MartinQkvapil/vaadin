package com.uhk.application.school.controller;

import com.uhk.application.school.model.entity.Course;
import com.uhk.application.school.model.entity.TeachingLanguages;
import com.uhk.application.school.model.entity.User;
import com.uhk.application.school.model.repository.*;
import com.uhk.application.school.model.validator.CourseValidator;
import com.uhk.application.school.model.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        userRepository.save(user);
    }

    @Override
    public void saveCourse(Course course) throws Exception {
        courseValidator.validate(course);
        courseRepository.save(course);
    }
}
