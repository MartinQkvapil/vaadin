package com.uhk.application.school.model.repository;


import com.uhk.application.school.model.entity.Course;
import com.uhk.application.school.model.entity.CourseToTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseToTestRepository extends JpaRepository<CourseToTest, Integer> {}