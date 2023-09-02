package com.lotrel.ltserwer.projectModule.protocol.request.sprint;

import com.lotrel.ltserwer.lotrelCommons.common.protocol.BaseRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CloseSprintRequest extends BaseRequest {
    private Long sprintId;
}
