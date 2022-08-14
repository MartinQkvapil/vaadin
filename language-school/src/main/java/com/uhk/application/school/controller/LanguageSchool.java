package com.uhk.application.school.controller;

import com.uhk.application.school.model.entity.TeachingLanguages;
import com.uhk.application.school.model.entity.User;

import java.util.List;

public interface LanguageSchool {
    User getUserByName(String name);

    List<TeachingLanguages> getAllLanguages();

    void saveUser(User user) throws Exception;
}
