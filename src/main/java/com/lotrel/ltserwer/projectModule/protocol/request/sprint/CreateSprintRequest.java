package com.lotrel.ltserwer.projectModule.protocol.request.sprint;

import com.lotrel.ltserwer.lotrelCommons.common.protocol.BaseRequest;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class CreateSprintRequest extends BaseRequest {

    private Long projectId;

    private String description;

    private OffsetDateTime beginDate;
    private OffsetDateTime endDate;
}
