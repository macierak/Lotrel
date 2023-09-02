package com.lotrel.ltserwer.reportsModule.domain.report;

import com.lotrel.ltserwer.projectModule.domain.enumeration.UrgencyType;
import com.lotrel.ltserwer.projectModule.domain.enumeration.Workflow;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class ReportTaskResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = -6441181438354151664L;

    private long id;
    private String taskId;
    private String taskName;
    private String assignee;

    private UrgencyType urgencyType;
    private Workflow currentWorkflow;

    private Integer totalLoggedTime;
}
