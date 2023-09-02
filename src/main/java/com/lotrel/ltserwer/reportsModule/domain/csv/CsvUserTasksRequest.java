package com.lotrel.ltserwer.reportsModule.domain.csv;

import com.lotrel.ltserwer.lotrelCommons.common.protocol.BaseRequest;
import com.lotrel.ltserwer.projectModule.domain.enumeration.SortDirection;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.OffsetDateTime;

@Data
public class CsvUserTasksRequest extends BaseRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 8131422055608125439L;

    private long userId;

    private OffsetDateTime dateFrom;
    private OffsetDateTime dateTo;

    @Pattern(regexp = "taskName|taskId|currentWorkflow|urgencyType|assignee")
    private String sortBy = "taskName";

    private SortDirection direction = SortDirection.ASC;

}
