package com.lotrel.ltserwer.reportsModule.domain.csv;

import com.lotrel.ltserwer.lotrelCommons.common.protocol.BaseRequest;
import com.lotrel.ltserwer.projectModule.domain.enumeration.SortDirection;
import com.lotrel.ltserwer.projectModule.domain.enumeration.UrgencyType;
import com.lotrel.ltserwer.projectModule.domain.enumeration.Workflow;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
public class CsvProjectRequest extends BaseRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = -1233096197726069925L;

    private long projectId;

    private OffsetDateTime dateFrom;
    private OffsetDateTime dateTo;

    private List<Workflow> taskWorkflows;
    private List<UrgencyType> taskTypes;

    @Pattern(regexp = "taskName|taskId|currentWorkflow|urgencyType|assignee")
    private String sortBy = "taskName";

    private SortDirection direction = SortDirection.ASC;
}
