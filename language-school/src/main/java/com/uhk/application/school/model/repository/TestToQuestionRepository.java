package com.uhk.application.school.model.repository;

import com.uhk.application.school.model.entity.CourseToTest;
import com.uhk.application.school.model.entity.Test;
import com.uhk.application.school.model.entity.TestToQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestToQuestionRepository extends JpaRepository<TestToQuestion, Integer> {
    @Query(value = "select t2q.id_test_to_question from test_to_question t2q " +
        "join test t ON t.id_test = t2q.id_test " +
        "where t2q.id_test = ?1", nativeQuery = true)
    Iterable<Integer> getByTestId(int idTest);

    @Query(value = "select * " +
            "from test_to_question t2q " +
            "where t2q.id_test = ?1 and t2q.id_question = ?2",
            nativeQuery = true)
    TestToQuestion getByTestIdAndQuestionId(int idTest, int idQuestion);
}
