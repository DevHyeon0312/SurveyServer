package com.devhyeon.survey.survey.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * 문항
 * ColumnName   Type                Description
 * ---------------------------------------------
 * id           [int]               설문아이디
 * question_id  [int]               문항아이디
 * question_msg [varchar(1024)]     문항내용
 * ---------------------------------------------
 * Primary : (id, question_id)      복합키
 * */

@Data
@Entity
@Table(name = "survey_question")
@IdClass(QuestionPK.class)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionEntity {
    @Id
    @Column(name = "id")
    private long id;

    @Id
    @Column(name = "question_id")
    private long question_id;

    @NotEmpty(message = "Title is not null")
    @Column(name = "question_msg")
    private String question_msg;
}

@Data
class QuestionPK implements Serializable {
    private long id;
    private long question_id;
}