package com.uhk.application.school.model.repository;


import com.uhk.application.school.model.entity.Course;
import com.uhk.application.school.model.entity.CourseToTest;
import com.uhk.application.school.model.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseToTestRepository extends JpaRepository<CourseToTest, Integer> {
    @Query(value = "select c2t.id_course_to_test " +
            "from course_to_test c2t " +
            "join test t ON t.id_test = c2t.id_test " +
            "where c2t.id_test = ?1",
            nativeQuery = true)
    Iterable<Integer> getByTestId(int idTest);
}