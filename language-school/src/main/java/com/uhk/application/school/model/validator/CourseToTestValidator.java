package com.uhk.application.school.model.validator;

import com.uhk.application.school.exception.CourseException;
import com.uhk.application.school.model.entity.CourseToTest;
import org.springframework.stereotype.Service;

@Service
public class CourseToTestValidator {
    public void validate(CourseToTest courseToTest) throws Exception {
        if (courseToTest == null) {
            throw new CourseException("Neexistuje kurz to test.");
        }
    }
}
