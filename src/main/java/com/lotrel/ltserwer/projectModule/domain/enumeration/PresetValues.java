package com.lotrel.ltserwer.projectModule.domain.enumeration;

import lombok.Getter;

@Getter
public enum PresetValues {

    TASK_BASE_URL("/task/");

    public final String value;

    PresetValues(String value) {
        this.value = value;
    }

}
