package com.uhk.application.school.model.validator;

import com.uhk.application.school.exception.CourseException;
import com.uhk.application.school.exception.UserException;
import com.uhk.application.school.model.entity.Test;
import com.uhk.application.school.model.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.testng.TestException;

@Service
public class TestValidator {

    @Autowired
    private TestRepository testRepository;

    public void validate(Test test) throws Exception {
        if (test == null) {
            throw new CourseException("Neexistuje test.");
        }
        if (test.getIdTest() == 0) {
            if (testRepository.findAllByUserId(test.getIdTest()) != null){
                throw new TestException("Uživatel má již tento test přidělený.");
            }
        }
    }
}
