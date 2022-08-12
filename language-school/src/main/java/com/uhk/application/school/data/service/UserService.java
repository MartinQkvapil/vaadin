package com.uhk.application.school.data.service;

import com.uhk.application.school.data.entity.User;

import com.uhk.application.school.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User getUserByUsername(String userName) {
        return userRepository.findByUsername(userName);
    }

    public int count() {
        return (int) userRepository.count();
    }

}
