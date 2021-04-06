package com.devhyeon.survey.survey.model;

import com.devhyeon.survey.survey.entity.AnswerEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Answer {
    private long answer_id;
    private String answer_msg;

    public AnswerEntity toEntity(long id, long question_id) {
        return AnswerEntity.builder()
                .id(id)
                .question_id(question_id)
                .answer_id(this.answer_id)
                .answer_msg(this.answer_msg)
                .build();
    }
}
