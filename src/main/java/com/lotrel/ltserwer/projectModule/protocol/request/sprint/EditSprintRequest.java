package com.lotrel.ltserwer.projectModule.protocol.request.sprint;

import com.lotrel.ltserwer.lotrelCommons.common.protocol.BaseRequest;
import jakarta.annotation.Nullable;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class EditSprintRequest extends BaseRequest {
    private Long sprintId;

    @Nullable
    private OffsetDateTime beginDate;

    @Nullable
    private OffsetDateTime endDate;

    @Nullable
    private String description;

}
