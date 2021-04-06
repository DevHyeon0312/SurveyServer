package com.devhyeon.survey.survey.model;


import com.devhyeon.survey.survey.entity.UserAnswerEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAns {
    private long questionId;
    private long answerId;

    public UserAnswerEntity toEntity(long user_id, long id) {
        return UserAnswerEntity.builder()
                .user_id(user_id)
                .id(id)
                .question_id(this.questionId)
                .answer_id(this.answerId)
                .build();
    }
}