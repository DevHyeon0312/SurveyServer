# SurveyServer
- 설문조사 백엔드 : Springboot + JPA + MySQL -> with NAT


### Rest API
|주소|타입|기능||
|:-------------|:-------:|:-----:|:--------:|
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
            <td rowspan=2><center>survey_title</center></td>
            <td><center>id</center></td>
            <td><center>bigint</center></td>
            <td><center>설문의 아이디</center></td>
            <td><center>✔</center></td>
        </tr>
        <tr>
            <td><center>title</center></td>
            <td><center>varchar(1024)</center></td>
            <td><center>설문의 제목</center></td>
            <td><center></center></td>
        </tr>
        <tr>
            <td rowspan=3><center>survey_question</center></td>
            <td><center>id</center></td>
            <td><center>bigint</center></td>
            <td><center>설문의 아이디</center></td>
            <td><center>✔</center></td>
        </tr>
        <tr>
            <td><center>question_id</center></td>
            <td><center>bigint</center></td>
            <td><center>문항의 아이디</center></td>
            <td><center>✔</center></td>
        </tr>
        <tr>
            <td><center>question_msg</center></td>
            <td><center>varchar(1024)</center></td>
            <td><center>문항의 내용</center></td>
            <td><center></center></td>
        </tr>
        <tr>
            <td rowspan=4><center>survey_answer</center></td>
            <td><center>id</center></td>
            <td><center>bigint</center></td>
            <td><center>설문의 아이디</center></td>
            <td><center>✔</center></td>
        </tr>
        <tr>
            <td><center>question_id</center></td>
            <td><center>bigint</center></td>
            <td><center>문항의 아이디</center></td>
            <td><center>✔</center></td>
        </tr>
        <tr>
            <td><center>answer_id</center></td>
            <td><center>bigint</center></td>
            <td><center>세부문항의 아이디</center></td>
            <td><center>✔</center></td>
        </tr>
        <tr>
            <td><center>answer_msg</center></td>
            <td><center>varchar(1024)</center></td>
            <td><center>세부문항의 내용</center></td>
            <td><center></center></td>
        </tr>
        <tr>
            <td rowspan=4><center>survey_user</center></td>
            <td><center>user_id</center></td>
            <td><center>bigint</center></td>
            <td><center>유저의 아이디</center></td>
            <td><center>✔</center></td>
        </tr>
        <tr>
            <td><center>id</center></td>
            <td><center>bigint</center></td>
            <td><center>설문의 아이디</center></td>
            <td><center>✔</center></td>
        </tr>
        <tr>
            <td><center>question_id</center></td>
            <td><center>bigint</center></td>
            <td><center>문항의 아이디</center></td>
            <td><center>✔</center></td>
        </tr>
        <tr>
            <td><center>answer_id</center></td>
            <td><center>bigint</center></td>
            <td><center>세부문항의 아이디</center></td>
            <td><center></center></td>
        </tr>
    </tbody>
</table>

