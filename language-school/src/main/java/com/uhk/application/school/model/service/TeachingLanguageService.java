package com.uhk.application.school.model.service;

import com.uhk.application.school.model.entity.TeachingLanguages;
import com.uhk.application.school.model.repository.TeachingLanguagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeachingLanguageService {

    private final TeachingLanguagesRepository teachingLanguagesRepository;

    public TeachingLanguageService(TeachingLanguagesRepository teachingLanguagesRepository) {
        this.teachingLanguagesRepository = teachingLanguagesRepository;
    }

    public List<TeachingLanguages> findAll() {
        return teachingLanguagesRepository.findAll();
    }
}