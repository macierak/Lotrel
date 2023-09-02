package com.lotrel.ltserwer.projectModule.protocol.request.project;

import lombok.Data;

@Data
public class FindUsersInProjectSpecificationRequest {
    private long projectId;
    private String value;
}
