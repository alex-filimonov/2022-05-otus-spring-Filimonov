package ru.otus.spring.spring02.enums;

public enum ExamResult {
    PASSED("passed"),
    UNKNOWN("unknown"),
    NOT_PASS("did not pass");
    private String name;

    ExamResult(String name) {
        this.name = name;
    }
}
