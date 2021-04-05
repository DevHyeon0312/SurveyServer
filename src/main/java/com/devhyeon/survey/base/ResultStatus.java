package com.devhyeon.survey.base;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResultStatus {
    private int messageCode;
    private String messageText;
    private Object messageTrace;
    private String messageClass;
}