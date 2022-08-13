package com.uhk.application.school.model.repository;

import com.uhk.application.school.model.entity.TeachingLanguages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeachingLanguagesRepository extends JpaRepository<TeachingLanguages, Integer> {
    List<TeachingLanguages> findAll();
}