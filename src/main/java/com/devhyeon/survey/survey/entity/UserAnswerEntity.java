package com.devhyeon.survey.survey.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 유저 답변
 * ColumnName   Type                Description
 * ---------------------------------------------
 * user_id      [bigint]               유저아이디
 * id           [bigint]               설문아이디
 * question_id  [bigint]               문항아이디
 * answer_id    [bigint]               답변아이디
 * ---------------------------------------------
 * Primary : (user_id, id, question_id) 복합키
 * */

@Data
@Entity
@Table(name = "survey_user")
@IdClass(UserPK.class)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserAnswerEntity {
    @Id
    @Column(name = "user_id")
    private long user_id;

    @Id
    @Column(name = "id")
    private long id;

    @Id
    @Column(name = "question_id")
    private long question_id;

    @Column(name = "answer_id")
    private long answer_id;
}

@Data
class UserPK implements Serializable {
    private long user_id;
    private long id;
    private long question_id;
    private long answer_id;
}