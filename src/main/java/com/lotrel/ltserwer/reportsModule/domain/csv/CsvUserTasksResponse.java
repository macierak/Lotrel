package com.lotrel.ltserwer.reportsModule.domain.csv;

import com.lotrel.ltserwer.reportsModule.domain.report.ReportTaskResponse;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
public class CsvUserTasksResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = -8604836257332009879L;

    private String username;

    private List<ReportTaskResponse> tasks;

    private boolean active;
}
