package com.uhk.application.school.model.repository;

import com.uhk.application.school.model.entity.Question;
import com.uhk.application.school.model.entity.TeachingLanguages;
import com.uhk.application.school.model.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
    List<Question> findAll();

    @Query(value = "select * from question q " +
            "join test_to_question t2q ON q.id_question = t2q.id_question " +
            "join test t ON t2q.id_test = t.id_test " +
            "where t.id_test = ?1", nativeQuery = true)
    List<Question> findAllByTestId(int idTest);
}

