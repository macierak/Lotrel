package com.lotrel.ltserwer.projectModule.protocol.request.task;

import com.lotrel.ltserwer.lotrelCommons.common.protocol.BaseRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogTimeRequest extends BaseRequest {
    private Integer timeInMinutes;
    private Long taskId;
}
