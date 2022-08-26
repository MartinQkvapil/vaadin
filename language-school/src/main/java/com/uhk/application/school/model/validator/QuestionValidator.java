package com.uhk.application.school.model.validator;

import com.uhk.application.school.exception.QuestionException;
import com.uhk.application.school.exception.UserException;
import com.uhk.application.school.model.entity.Question;
import com.uhk.application.school.model.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class QuestionValidator {
    public void validate(Question question) throws QuestionException {
        if (question == null) {
            throw new QuestionException("Neexistuje kurz.");
        }
        if (question.getQuestion().isEmpty() || Objects.equals(question.getQuestion(), "")) {
            throw new QuestionException("Otázku nelze uložit prázdnou.");
        }
        if (question.getAnswer1().isEmpty() || Objects.equals(question.getAnswer1(), "")) {
            throw new QuestionException("Otázku bez správné odpovědi nelse uložit.");
        }
    }
}
