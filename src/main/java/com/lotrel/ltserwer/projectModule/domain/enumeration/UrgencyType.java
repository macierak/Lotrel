package com.lotrel.ltserwer.projectModule.domain.enumeration;

import java.util.List;

public enum UrgencyType {
    BLOCKER,
    CRITICAL,
    MAJOR,
    MINOR,
    TRIVIAL;

    public static List<UrgencyType> returnAllTypes() {
        return List.of(BLOCKER, CRITICAL, MAJOR, MINOR, TRIVIAL);
    }
}
