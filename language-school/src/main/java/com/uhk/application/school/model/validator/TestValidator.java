package com.uhk.application.school.model.validator;

import com.uhk.application.school.exception.CourseException;
import com.uhk.application.school.model.entity.Course;
import com.uhk.application.school.model.entity.Test;
import com.uhk.application.school.model.repository.CourseRepository;
import com.uhk.application.school.model.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestValidator {
    @Autowired
    private TestRepository testRepository;

    public void validate(Test test) throws Exception {
        if (test == null) {
            throw new CourseException("Neexistuje test.");
        }
    }
}
