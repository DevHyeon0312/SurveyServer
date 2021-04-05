package com.devhyeon.survey.base;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.UnexpectedTypeException;
import java.util.ArrayList;
import java.util.Set;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleException(final Exception e) {
        final ApiResponse response = ApiResponse.builder()
                .resultData(null)
                .resultStatus(
                        ResultStatus.builder()
                                .messageCode(ApiStatus.RESULT_ERROR.key())
                                .messageText(ApiStatus.RESULT_ERROR.value())
                                .messageTrace("알수없음")
                                .messageClass(this.getClass().getSimpleName())
                                .build()
                ).build();

        System.out.println("ERROR"+response);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponse> handleConstraintViolationException(final ConstraintViolationException e) {
        ArrayList<String> message = new ArrayList<>();
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        for (ConstraintViolation<?> violation : violations ) {
            message.add(violation.getMessage());
        }

        final ApiResponse response = ApiResponse.builder()
                .resultData(null)
                .resultStatus(
                        ResultStatus.builder()
                                .messageCode(ApiStatus.RESULT_ERROR.key())
                                .messageText(ApiStatus.RESULT_ERROR.value())
                                .messageTrace(message)
                                .messageClass(this.getClass().getSimpleName())
                                .build()
                ).build();

        System.out.println("ERROR"+response);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(StackOverflowError.class)
    public ResponseEntity<ApiResponse> handleConstraintViolationException(final StackOverflowError e) {
        final ApiResponse response = ApiResponse.builder()
                .resultData(null)
                .resultStatus(
                        ResultStatus.builder()
                                .messageCode(ApiStatus.RESULT_ERROR.key())
                                .messageText(ApiStatus.RESULT_ERROR.value())
                                .messageTrace("StackOverflowError")
                                .messageClass(this.getClass().getSimpleName())
                                .build()
                ).build();

        System.out.println("ERROR"+response);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnexpectedTypeException.class)
    public ResponseEntity<ApiResponse> handleUnexpectedTypeException(final UnexpectedTypeException e) {
        final ApiResponse response = ApiResponse.builder()
                .resultData(null)
                .resultStatus(
                        ResultStatus.builder()
                                .messageCode(ApiStatus.RESULT_ERROR.key())
                                .messageText(ApiStatus.RESULT_ERROR.value())
                                .messageTrace("StackOverflowError")
                                .messageClass(this.getClass().getSimpleName())
                                .build()
                ).build();

        System.out.println("ERROR"+response);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse> handleHttpMessageNotReadableException(final HttpMessageNotReadableException e) {
        final ApiResponse response = ApiResponse.builder()
                .resultData(null)
                .resultStatus(
                        ResultStatus.builder()
                                .messageCode(ApiStatus.RESULT_ERROR.key())
                                .messageText(ApiStatus.RESULT_ERROR.value())
                                .messageTrace("StackOverflowError")
                                .messageClass(this.getClass().getSimpleName())
                                .build()
                ).build();

        System.out.println("ERROR"+response);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}