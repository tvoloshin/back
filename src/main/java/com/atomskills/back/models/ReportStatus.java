package com.atomskills.back.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ReportStatus {
    CREATED("Создано"),
    APPROVED("Согласовано"),
    REJECTED("Отклонено"),
    SEND("Отправлено");

    private final String value;
}
