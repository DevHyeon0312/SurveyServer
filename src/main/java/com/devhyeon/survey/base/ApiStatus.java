package com.devhyeon.survey.base;

public enum ApiStatus {
    RESULT_OK(1994,"요청이 정상적으로 처리되었습니다."),
    RESULT_ERROR(1996, "요청을 처리하는데 실패하였습니다.");

    private final int key;
    private final String value;
    ApiStatus(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public int key() {
        return this.key;
    }

    public String value(){
        return this.value;
    }
}