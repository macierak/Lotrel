package com.lotrel.ltserwer.projectModule.protocol.request.task;

import com.lotrel.ltserwer.lotrelCommons.common.protocol.BaseRequest;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateTaskRequest extends BaseRequest {

    @NotBlank
    private String taskName;

    @NotBlank
    private String projectKey;

    @Nullable
    private String description;

    private Long sprintId;
}
