package com.lotrel.ltserwer.projectModule.protocol.request.task;

import com.lotrel.ltserwer.lotrelCommons.common.protocol.BaseRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssignTaskToUserRequest extends BaseRequest {

    private Long userId;
    private Long taskId;
}
