package com.uhk.application.school.controller;

import com.uhk.application.school.data.entity.User;
import com.uhk.application.school.data.repository.*;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

@Controller
@Transactional
public class MainControllerImpl implements LanguageSchool{
    private final UserRepository userRepository;

    public MainControllerImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserByName(String name) {
    return userRepository.findByEmail(name);
    }

}
