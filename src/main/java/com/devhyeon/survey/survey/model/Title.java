package com.devhyeon.survey.survey.model;


import com.devhyeon.survey.survey.entity.TitleEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Title {
    @NotEmpty
    private String title;

    public TitleEntity toEntity() {
        return TitleEntity.builder()
                .title(this.title)
                .build();
    }
}
