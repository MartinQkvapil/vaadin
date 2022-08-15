package com.uhk.application.school.model.repository;

import com.uhk.application.school.model.entity.Test;
import com.uhk.application.school.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestRepository extends JpaRepository<Test, Integer> {
    @Query(value = "select * " +
            "from test t " +
            "join course_to_test t2c ON t.id_test = t2c.id_test " +
            "join course c ON t2c.id_course = c.id_course " +
            "join user u ON c.id_user = u.id_user " +
            "where u.id_user = ?1",
        nativeQuery = true)
    List<Test> findAllByUserId(int idUser);

    @Query(value = "select * " +
            "from test t " +
            "where t.id_teaching_lang = ?1",
            nativeQuery = true)
    List<Test> findAllByLanguageId(int idTeachingLanguage);
}
