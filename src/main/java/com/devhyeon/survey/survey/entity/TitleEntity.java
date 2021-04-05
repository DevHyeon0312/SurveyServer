package com.devhyeon.survey.survey.entity;

import jdk.jfr.Description;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

/**
 * 설문
 * ColumnName   Type                Description
 * ---------------------------------------------
 * id           [int]               설문아이디
 * title        [varchar(1024)]     설문제목
 * ---------------------------------------------
 * Primary : (id)                   기본키
 * */

@Data
@Entity
@Table(name = "survey_title")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TitleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @NotEmpty(message = "Title is not null")
    @Column(name = "title")
    private String title;
}
