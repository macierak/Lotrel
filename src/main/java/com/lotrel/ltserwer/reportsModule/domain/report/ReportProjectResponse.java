package com.lotrel.ltserwer.reportsModule.domain.report;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
public class ReportProjectResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = -4539790318581969284L;

    private long id;
    private String name;

    private List<ReportSprintResponse> sprints;

    private boolean active;
}
