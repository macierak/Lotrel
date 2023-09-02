package com.lotrel.ltserwer.reportsModule.domain.csv;

import com.lotrel.ltserwer.lotrelCommons.common.protocol.BaseRequest;
import com.lotrel.ltserwer.projectModule.domain.enumeration.SortDirection;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class CsvSprintRequest extends BaseRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = -1233096197726069925L;

    private long sprintId;

    @Pattern(regexp = "taskName|taskId|currentWorkflow|urgencyType|assignee")
    private String sortBy = "taskName";

    private SortDirection direction = SortDirection.ASC;
}
