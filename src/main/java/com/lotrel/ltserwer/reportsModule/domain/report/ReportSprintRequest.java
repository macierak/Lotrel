package com.lotrel.ltserwer.reportsModule.domain.report;

import com.lotrel.ltserwer.projectModule.domain.enumeration.SortDirection;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class ReportSprintRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = -31864760700371087L;

    private long sprintId;

    @Pattern(regexp = "taskName|taskId|currentWorkflow|urgencyType|assignee")
    private String sortBy = "taskName";

    private SortDirection direction = SortDirection.ASC;

}
