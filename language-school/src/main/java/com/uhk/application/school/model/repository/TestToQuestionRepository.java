package com.uhk.application.school.model.repository;

import com.uhk.application.school.model.entity.TestToQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestToQuestionRepository extends JpaRepository<TestToQuestion, Integer> {

}
