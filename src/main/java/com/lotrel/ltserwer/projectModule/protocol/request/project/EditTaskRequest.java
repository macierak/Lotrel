package com.lotrel.ltserwer.projectModule.protocol.request.project;

import com.lotrel.ltserwer.lotrelCommons.common.protocol.BaseRequest;

import com.lotrel.ltserwer.projectModule.domain.enumeration.UrgencyType;
import com.lotrel.ltserwer.projectModule.domain.enumeration.Workflow;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Null;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditTaskRequest extends BaseRequest {
    private Long taskId;
    @Nullable
    private String taskName;
    @Nullable
    private String taskDescription;
    @Nullable
    private Workflow workflow;
    @Nullable
    private UrgencyType urgencyType;

    @Nullable
    private Long sprintId;

    @Nullable
    private Long userId;


}
