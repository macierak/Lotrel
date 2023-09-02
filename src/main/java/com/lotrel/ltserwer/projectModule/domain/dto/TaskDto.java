package com.lotrel.ltserwer.projectModule.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lotrel.ltserwer.projectModule.domain.enumeration.UrgencyType;
import com.lotrel.ltserwer.projectModule.domain.enumeration.Workflow;
import com.lotrel.ltserwer.userModule.domain.dto.UserDto;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class TaskDto {
    private Long id;
    private String taskId;

    private String taskName;

    private String taskExternalUrl;

    private Workflow currentWorkflow;

    private boolean deleted;

    private String description;
    private UrgencyType urgencyType;

    private UserDto user;

    private SprintDto sprint;

    @JsonFormat(pattern="dd.MM.yyyy")
    private OffsetDateTime creationDate;
}
