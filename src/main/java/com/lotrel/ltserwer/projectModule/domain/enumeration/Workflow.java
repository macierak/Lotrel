package com.lotrel.ltserwer.projectModule.domain.enumeration;

import java.util.List;

public enum Workflow {
    OPEN,
    ANALYSIS,
    DEVELOPMENT,
    TESTING,
    TO_DEPLOY,
    CLOSE;

    public static List<Workflow> returnAllWorkflows() {
        return List.of(OPEN, ANALYSIS, DEVELOPMENT, TESTING, TO_DEPLOY, CLOSE);
    }
}
