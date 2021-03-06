# SurveyServer
- 설문조사 백엔드 : Springboot + JPA + MySQL -> with NAT

## 실행영상

![화면 기록 2021-04-06 오후 4 36 29](https://user-images.githubusercontent.com/72678200/113675805-b3f17880-96f6-11eb-96f8-2622da079554.gif)

------


### Rest API
|주소|타입|기능|
|:-------------|:-------:|:-----:|
|protocol:://addr:port/survey/createSurvey|POST|설문생성||
|protocol:://addr:port/survey/getSurveyTitle|GET|만들어진 설문 전체 조회||
|protocol:://addr:port/survey/getSurveyDetail?titleId=|GET|타이틀 아이디에 해당하는 설문 상세조회||
|protocol:://addr:port/survey/getResult?userId=&titleId=|GET|타이틀 아이디에 해당하는 결과 상세조회||
|protocol:://addr:port/survey/addResult|POST|설문 답변 등록||

<details>
<summary>설문생성 JSON 예시</summary>
<div markdown="1">
<pre class="brush: json" >
{ 
    "title": {
        "title":"선택이 필요합니다."
    } ,
    "questions":[
        {
            "question_id":1,
            "question_msg":"야식은 무엇을 먹을까요?",
            "answers" : [
                {
                    "answer_id":1,
                    "answer_msg":"1.치킨"
                },
                {
                    "answer_id":2,
                    "answer_msg":"2.족발"
                },
                {
                    "answer_id":3,
                    "answer_msg":"3.굶기"
                }
            ]
        } , 
        {
            "question_id":2,
            "question_msg":"오늘은 몇시에잘까요?",
            "answers" : [
                {
                    "answer_id":1,
                    "answer_msg":"22시59분"
                },
                {
                    "answer_id":2,
                    "answer_msg":"23시59분"
                },
                {
                    "answer_id":3,
                    "answer_msg":"내일자요"
                }
            ]
        },
        {
            "question_id":3,
            "question_msg":"어느 커피숍을 갈까요?",
            "answers" : [
                {
                    "answer_id":1,
                    "answer_msg":"스타벅스"
                },
                {
                    "answer_id":2,
                    "answer_msg":"이디야"
                },
                {
                    "answer_id":3,
                    "answer_msg":"메가"
                }
            ]
        }
    ]
}
</pre>
</div>
</details>

<details>
<summary>설문 답변 등록 JSON 예시</summary>
<div markdown="1">
<pre class="brush: json" >
{
    "userId":202104061549,
    "titleId":1,
    "userAnsList":[
        {
            "questionId":1,
            "answerId":2
        },
        {
            "questionId":2,
            "answerId":1
        }
    ],
    "countList":null
}
</pre>
</div>
</details>

#

------

### DB [ Table & Column ]
|테이블명|간단설명|
|:-------------:|:-------:|
|survey_title|설문의 아이디와 제목|
|survey_question|문항의 아이디와 내용|
|survey_answer|세부문항의 아이디와 내용|
|survey_user|유저의 아이디와 답변|

<table>
    <thead>
        <tr>
            <th>테이블명</th>
            <th>컬럼명</th>
            <th>타입</th>
            <th>설명</th>
            <th>기본키 or 복합키</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td rowspan=2 style="text-align: center">survey_title</td>
            <td style="text-align: center">id</td>
            <td style="text-align: center">bigint</td>
            <td style="text-align: center">설문의 아이디</td>
            <td style="text-align: center">✔</td>
        </tr>
        <tr>
            <td style="text-align: center">title</td>
            <td style="text-align: center">varchar(1024)</td>
            <td style="text-align: center">설문의 제목</td>
            <td style="text-align: center"></td>
        </tr>
        <tr>
            <td rowspan=3 style="text-align: center">survey_question</td>
            <td style="text-align: center">id</td>
            <td style="text-align: center">bigint</td>
            <td style="text-align: center">설문의 아이디</td>
            <td style="text-align: center">✔</td>
        </tr>
        <tr>
            <td style="text-align: center">question_id</td>
            <td style="text-align: center">bigint</td>
            <td style="text-align: center">문항의 아이디</td>
            <td style="text-align: center">✔</td>
        </tr>
        <tr>
            <td style="text-align: center">question_msg</td>
            <td style="text-align: center">varchar(1024)</td>
            <td style="text-align: center">문항의 내용</td>
            <td style="text-align: center"></td>
        </tr>
        <tr>
            <td rowspan=4 style="text-align: center">survey_answer</td>
            <td style="text-align: center">id</td>
            <td style="text-align: center">bigint</td>
            <td style="text-align: center">설문의 아이디</td>
            <td style="text-align: center">✔</td>
        </tr>
        <tr>
            <td style="text-align: center">question_id</td>
            <td style="text-align: center">bigint</td>
            <td style="text-align: center">문항의 아이디</td>
            <td style="text-align: center">✔</td>
        </tr>
        <tr>
            <td style="text-align: center">answer_id</td>
            <td style="text-align: center">bigint</td>
            <td style="text-align: center">세부문항의 아이디</td>
            <td style="text-align: center">✔</td>
        </tr>
        <tr>
            <td style="text-align: center">answer_msg</td>
            <td style="text-align: center">varchar(1024)</td>
            <td style="text-align: center">세부문항의 내용</td>
            <td style="text-align: center"></td>
        </tr>
        <tr>
            <td rowspan=4 style="text-align: center">survey_user</td>
            <td style="text-align: center">user_id</td>
            <td style="text-align: center">bigint</td>
            <td style="text-align: center">유저의 아이디</td>
            <td style="text-align: center">✔</td>
        </tr>
        <tr>
            <td style="text-align: center">id</td>
            <td style="text-align: center">bigint</td>
            <td style="text-align: center">설문의 아이디</td>
            <td style="text-align: center">✔</td>
        </tr>
        <tr>
            <td style="text-align: center">question_id</td>
            <td style="text-align: center">bigint</td>
            <td style="text-align: center">문항의 아이디</td>
            <td style="text-align: center">✔</td>
        </tr>
        <tr>
            <td style="text-align: center">answer_id</td>
            <td style="text-align: center">bigint</td>
            <td style="text-align: center">세부문항의 아이디</td>
            <td style="text-align: center"></td>
        </tr>
    </tbody>
</table>

