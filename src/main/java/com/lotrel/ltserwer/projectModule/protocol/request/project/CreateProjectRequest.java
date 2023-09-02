package com.lotrel.ltserwer.projectModule.protocol.request.project;

import com.lotrel.ltserwer.lotrelCommons.common.protocol.BaseRequest;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProjectRequest extends BaseRequest {

    @NotBlank
    private String projectKey;

    @NotBlank
    private String projectName;
}
