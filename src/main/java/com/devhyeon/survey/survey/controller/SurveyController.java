package com.devhyeon.survey.survey.controller;

import com.devhyeon.survey.base.ApiResponse;
import com.devhyeon.survey.base.ApiStatus;
import com.devhyeon.survey.base.ResultStatus;
import com.devhyeon.survey.survey.entity.AnswerEntity;
import com.devhyeon.survey.survey.entity.QuestionEntity;
import com.devhyeon.survey.survey.entity.TitleEntity;
import com.devhyeon.survey.survey.entity.UserAnswerEntity;
import com.devhyeon.survey.survey.model.*;
import com.devhyeon.survey.survey.repository.AnswerRepository;
import com.devhyeon.survey.survey.repository.QuestionRepository;
import com.devhyeon.survey.survey.repository.TitleRepository;
import com.devhyeon.survey.survey.repository.UserAnsRepository;
import jdk.jfr.Description;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/survey")
public class SurveyController {
    private TitleRepository titleRepository;
    private QuestionRepository questionRepository;
    private AnswerRepository answerRepository;
    private UserAnsRepository userAnsRepository;

    @Autowired
    SurveyController(TitleRepository titleRepository,
                     QuestionRepository questionRepository,
                     AnswerRepository answerRepository,
                     UserAnsRepository userAnsRepository) {
        this.titleRepository = titleRepository;
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.userAnsRepository = userAnsRepository;
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

    @Transactional
    @Description("설문결과 조회")
    @GetMapping("getResult")
    public ApiResponse getResultDetail(@RequestParam long userId, @RequestParam long titleId) {
        ApiResponse response;

        List<UserAnswerEntity> answerEntities = userAnsRepository.findAll().stream().filter(obj->obj.getId() == titleId).collect(Collectors.toList());

        SurveyResult result = new SurveyResult();
        result.setUserId(userId);
        result.setTitleId(titleId);
        List<UserAns> resultList = new ArrayList<>();
        boolean isUser = false;
        HashMap<Long,HashMap<Long,Integer>> hashMap = new HashMap<>();
        for (UserAnswerEntity entity : answerEntities) {
            isUser = entity.getUser_id() == userId || isUser;
            resultList.add(new UserAns(entity.getQuestion_id(),entity.getAnswer_id()));
            //question 문항을
            if (!hashMap.containsKey(entity.getQuestion_id())) {
                hashMap.put(entity.getQuestion_id(), new HashMap<>());
            }
            //answer 번호로 답한 인원
            hashMap.get(entity.getQuestion_id()).merge(entity.getAnswer_id(),1, Integer::sum);
        }
        result.setUserAnsList(null);
        result.setCountList(hashMap);

        if (isUser) {
            response = ApiResponse.builder()
                    .resultData(result)
                    .resultStatus(
                            ResultStatus.builder()
                                    .messageCode(ApiStatus.RESULT_OK.key())
                                    .messageText(ApiStatus.RESULT_OK.value())
                                    .build()
                    ).build();
        } else {
            response = ApiResponse.builder()
                    .resultData(null)
                    .resultStatus(
                            ResultStatus.builder()
                                    .messageCode(ApiStatus.RESULT_OK.key())
                                    .messageText(ApiStatus.RESULT_OK.value())
                                    .build()
                    ).build();
        }

        return response;
    }

    @Transactional
    @Description("설문결과 등록")
    @PostMapping("addResult")
    public ApiResponse CreateResult(@RequestBody @Valid SurveyResult surveyResult) {
        final ApiResponse response = ApiResponse.builder().resultData("답변등록이 완료되었습니다.")
                .resultStatus(
                        ResultStatus.builder()
                                .messageCode(ApiStatus.RESULT_OK.key())
                                .messageText(ApiStatus.RESULT_OK.value())
                                .build()
                ).build();
        long userId = surveyResult.getUserId();
        long titleId = surveyResult.getTitleId();
        for (UserAns userAns : surveyResult.getUserAnsList()) {
            userAnsRepository.save(userAns.toEntity(userId, titleId));
        }
        return response;
    }
}