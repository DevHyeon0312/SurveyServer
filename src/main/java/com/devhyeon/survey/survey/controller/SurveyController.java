package com.devhyeon.survey.survey.controller;

import com.devhyeon.survey.base.ApiResponse;
import com.devhyeon.survey.base.ApiStatus;
import com.devhyeon.survey.base.ResultStatus;
import com.devhyeon.survey.survey.entity.AnswerEntity;
import com.devhyeon.survey.survey.entity.QuestionEntity;
import com.devhyeon.survey.survey.model.Answer;
import com.devhyeon.survey.survey.model.Question;
import com.devhyeon.survey.survey.model.Survey;
import com.devhyeon.survey.survey.entity.TitleEntity;
import com.devhyeon.survey.survey.model.Title;
import com.devhyeon.survey.survey.repository.AnswerRepository;
import com.devhyeon.survey.survey.repository.QuestionRepository;
import com.devhyeon.survey.survey.repository.TitleRepository;
import jdk.jfr.Description;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.*;

import javax.persistence.*;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/survey")
public class SurveyController {
    private TitleRepository titleRepository;
    private QuestionRepository questionRepository;
    private AnswerRepository answerRepository;

    @Autowired
    SurveyController(TitleRepository titleRepository,
                     QuestionRepository questionRepository,
                     AnswerRepository answerRepository) {
        this.titleRepository = titleRepository;
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
    }

    @Description("만들어진 설문 조회")
    @GetMapping("getSurveyTitle")
    public ApiResponse getSurveyTitleAll() {
        List<TitleEntity> titleEntity = titleRepository.findAll();
        final ApiResponse response = ApiResponse.builder()
                .resultData(titleEntity)
                .resultStatus(
                        ResultStatus.builder()
                                .messageCode(ApiStatus.RESULT_OK.key())
                                .messageText(ApiStatus.RESULT_OK.value())
                                .messageClass(this.getClass().getSimpleName())
                                .build()
                ).build();
        return response;
    }

    @Transactional
    @Description("설문 상세조회")
    @GetMapping("getSurveyDetail")
    public ApiResponse getSurveyDetail(@RequestParam long titleId) {
        Survey survey = new Survey();
        /* TITLE */
        TitleEntity titleEntity = titleRepository.findById(titleId).get();
        Title title = new Title();
        title.setTitle(titleEntity.getTitle());
        survey.setTitle(title);
        System.out.println(survey);

        /* Question */
        List<QuestionEntity> queryResultList =  questionRepository.findAll().stream().filter(obj->obj.getId()==titleId).collect(Collectors.toList());
        /* Answer */
        List<AnswerEntity> answerList = answerRepository.findAll().stream().filter(obj->obj.getId()==titleId).collect(Collectors.toList());

        List<Question> questions = new ArrayList<>();
        for(QuestionEntity item : queryResultList) {
            System.out.println(item);
            Question question = new Question();
            question.setQuestion_id(item.getQuestion_id());
            question.setQuestion_msg(item.getQuestion_msg());
            List<Answer> answers = new ArrayList<>();
            for (AnswerEntity answerEntity : answerList.stream().filter(obj->obj.getQuestion_id() == item.getQuestion_id()).collect(Collectors.toList())) {
                Answer answer = new Answer();
                answer.setAnswer_id(answerEntity.getAnswer_id());
                answer.setAnswer_msg(answerEntity.getAnswer_msg());
                answers.add(answer);
            }
            question.setAnswers(answers);
            questions.add(question);
        }
        survey.setQuestions(questions);

        final ApiResponse response = ApiResponse.builder()
                .resultData(survey)
                .resultStatus(
                        ResultStatus.builder()
                                .messageCode(ApiStatus.RESULT_OK.key())
                                .messageText(ApiStatus.RESULT_OK.value())
                                .messageClass(this.getClass().getSimpleName())
                                .build()
                ).build();
        return response;
    }


    @Transactional
    @Description("설문생성")
    @PostMapping("createSurvey")
    public ApiResponse CreateTitle(@RequestBody Survey survey) {
        final ApiResponse response = ApiResponse.builder()
                .resultData("설문생성이 완료되었습니다.")
                .resultStatus(
                        ResultStatus.builder()
                                .messageCode(ApiStatus.RESULT_OK.key())
                                .messageText(ApiStatus.RESULT_OK.value())
                                .messageClass(this.getClass().getSimpleName())
                                .build()
                ).build();
        TitleEntity title  = titleRepository.save(survey.getTitle().toEntity());
        long titleId = title.getId();
        for (Question question : survey.getQuestions()) {
            questionRepository.save(question.toEntity(titleId));
            long questionId = question.getQuestion_id();
            for (Answer answer : question.getAnswers()) {
                System.out.println(answer);
                answerRepository.save(answer.toEntity(titleId,questionId));
            }
        }
        return response;
    }
}