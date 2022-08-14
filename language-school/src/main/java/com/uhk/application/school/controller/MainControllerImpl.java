package com.uhk.application.school.controller;

import com.uhk.application.school.model.entity.TeachingLanguages;
import com.uhk.application.school.model.entity.User;
import com.uhk.application.school.model.repository.*;
import com.uhk.application.school.model.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Controller
@Transactional
public class MainControllerImpl implements LanguageSchool{
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final UserValidator userValidator;

    @Autowired
    private final TeachingLanguagesRepository teachingLanguagesRepository;

    public MainControllerImpl(
            UserRepository userRepository,
            UserValidator userValidator,
            TeachingLanguagesRepository teachingLanguagesRepository
    ) {
        this.userRepository = userRepository;
        this.userValidator = userValidator;
        this.teachingLanguagesRepository = teachingLanguagesRepository;
    }

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
}
