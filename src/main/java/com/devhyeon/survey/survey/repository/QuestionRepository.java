package com.devhyeon.survey.survey.repository;

import com.devhyeon.survey.survey.entity.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
public interface QuestionRepository extends JpaRepository<QuestionEntity, Long> {
}