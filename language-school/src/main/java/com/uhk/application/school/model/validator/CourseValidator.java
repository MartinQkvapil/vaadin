package com.uhk.application.school.model.validator;

import com.uhk.application.school.exception.CourseException;
import com.uhk.application.school.exception.UserException;
import com.uhk.application.school.model.entity.Course;
import com.uhk.application.school.model.entity.User;
import com.uhk.application.school.model.repository.CourseRepository;
import com.uhk.application.school.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CourseValidator {
    @Autowired
    private CourseRepository courseRepository;

    public void validate(Course course) throws Exception {
        if (course == null) {
            throw new CourseException("Neexistuje kurz");
        }
    }
}
