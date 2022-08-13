package com.uhk.application.school.model.service;

import com.uhk.application.school.model.repository.TestToQuestionRepository;
import org.springframework.stereotype.Service;

@Service
public class TestToQuestionService {
    private final TestToQuestionRepository testToQuestionRepository;

    public TestToQuestionService(TestToQuestionRepository testToQuestionRepository) {
        this.testToQuestionRepository = testToQuestionRepository;
    }
}
