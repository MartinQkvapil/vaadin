package com.uhk.application.school.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestToQuestion extends JpaRepository<TestToQuestion, Integer> {
}
