package com.uhk.application.school.model.repository;

import com.uhk.application.school.model.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
    @Query(value = "select * " +
            "from course c " +
            "where c.id_user = ?1 and c.id_teaching_lang = ?2 ",
            nativeQuery = true)
    Course findByUserIdAndTeachingLanguage(int idUser, int idTeachingLanguage);
}