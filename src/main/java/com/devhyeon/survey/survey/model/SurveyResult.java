package com.devhyeon.survey.survey.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SurveyResult {
    private long userId;
    private long titleId;
    private List<UserAns> userAnsList;
    private HashMap<Long,HashMap<Long,Integer>> countList;
}
