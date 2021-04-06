package com.devhyeon.survey.survey.repository;

import com.devhyeon.survey.survey.entity.UserAnswerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAnsRepository extends JpaRepository<UserAnswerEntity, Long> { }