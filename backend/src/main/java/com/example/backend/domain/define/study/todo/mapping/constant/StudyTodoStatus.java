package com.example.backend.domain.define.study.todo.mapping.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum StudyTodoStatus {
    TODO_INCOMPLETE("미완료"),
    TODO_COMPLETE("완료"),
    ;

    private final String text;
}
