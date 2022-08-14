package com.uhk.application.school.controller;

import com.uhk.application.school.model.entity.TeachingLanguages;
import com.uhk.application.school.model.entity.User;
import com.uhk.application.school.model.repository.*;
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
    private final TeachingLanguagesRepository teachingLanguagesRepository;

    public MainControllerImpl(UserRepository userRepository, TeachingLanguagesRepository teachingLanguagesRepository) {
        this.userRepository = userRepository;
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
    public void saveUser(User user) {
        userRepository.save(user);
    }
}
