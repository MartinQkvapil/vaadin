package com.uhk.application.school.model.repository;

import com.uhk.application.school.model.entity.Question;
import com.uhk.application.school.model.entity.TeachingLanguages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
    List<Question> findAll();
}

