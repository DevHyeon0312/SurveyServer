package com.devhyeon.survey.survey.model;


import com.devhyeon.survey.survey.entity.QuestionEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question {
    private long question_id;
    private String question_msg;
    private List<Answer> answers;

    public QuestionEntity toEntity(long id) {
        return QuestionEntity.builder()
                .id(id)
                .question_id(this.question_id)
                .question_msg(this.question_msg)
                .build();
    }
}
