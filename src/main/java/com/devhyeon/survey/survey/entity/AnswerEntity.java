package com.devhyeon.survey.survey.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * 답변 목록
 * ColumnName   Type                Description
 * ---------------------------------------------
 * id           [bigint]               설문아이디
 * question_id  [bigint]               문항아이디
 * answer_id    [bigint]               답변아이디
 * answer_msg   [varchar(1024)]        답변내용
 * ---------------------------------------------
 * Primary : (id, question_id)      복합키
 * */
@Data
@Entity
@Table(name = "survey_answer")
@IdClass(AnswerPK.class)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnswerEntity {
    @Id
    @Column(name = "answer_id")
    private long answer_id;

    @Id
    @Column(name = "id")
    private long id;

    @Id
    @Column(name = "question_id")
    private long question_id;


    @NotEmpty(message = "Title is not null")
    @Column(name = "answer_msg")
    private String answer_msg;
}

@Data
class AnswerPK implements Serializable {
    private long answer_id;
    private long id;
    private long question_id;
}