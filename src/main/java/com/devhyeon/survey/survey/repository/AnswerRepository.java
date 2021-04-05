package com.devhyeon.survey.survey.repository;

import com.devhyeon.survey.survey.entity.AnswerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<AnswerEntity, Long> { }