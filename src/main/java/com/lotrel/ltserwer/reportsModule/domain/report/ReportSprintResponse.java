package com.lotrel.ltserwer.reportsModule.domain.report;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;

@Data
public class ReportSprintResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = -4539790318581969284L;

    private long id;
    private String description;

    private List<ReportTaskResponse> tasks;

    private boolean active;

    @JsonFormat(pattern="dd.MM.yyyy")
    private OffsetDateTime beginDate;

    @JsonFormat(pattern="dd.MM.yyyy")
    private OffsetDateTime endDate;
}
