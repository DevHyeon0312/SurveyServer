package com.devhyeon.survey.survey.model;

import com.devhyeon.survey.survey.model.Answer;
import com.devhyeon.survey.survey.model.Question;
import com.devhyeon.survey.survey.model.Title;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Survey {
    private Title title;
    private List<Question> questions;
}