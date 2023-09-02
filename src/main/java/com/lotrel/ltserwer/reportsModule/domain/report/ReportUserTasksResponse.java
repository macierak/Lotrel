package com.lotrel.ltserwer.reportsModule.domain.report;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
public class ReportUserTasksResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = -4539790318581969284L;

    private String username;

    private List<ReportTaskResponse> tasks;

    private boolean active;
}
