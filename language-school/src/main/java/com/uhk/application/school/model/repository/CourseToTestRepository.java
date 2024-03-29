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
    @Query(value = "select * " +
            "from course_to_test c2t " +
            "join test t ON t.id_test = c2t.id_test " +
            "join course c ON c.id_course = c2t.id_course " +
            "where c.id_user = ?1 and t.id_test = ?2",
            nativeQuery = true)
    CourseToTest findByUserIdAndTestId(int idUser, int idTest);

    @Query(value = "select * " +
        "from course_to_test c2t " +
        "where c2t.id_course = ?1 and c2t.id_test = ?2",
        nativeQuery = true)
    CourseToTest findByCourseIdAndTestId(int idCourse, int idTest);

    @Query(value = "select COALESCE(sum(c2t.wrong_answers), 0) AS wrong_answers " +
            "from course_to_test c2t " +
            "join course c ON c.id_course = c2t.id_course " +
            "where c.id_user = ?1 ", nativeQuery = true)
    int countWrongByUserId(int idUser);

    @Query(value = "select COALESCE(sum(c2t.correct_answers), 0) AS correct_answers " +
            "from course_to_test c2t " +
            "join course c ON c.id_course = c2t.id_course " +
            "where c.id_user = ?1 ", nativeQuery = true)
    int countCorrectByUserId(int idUser);
}