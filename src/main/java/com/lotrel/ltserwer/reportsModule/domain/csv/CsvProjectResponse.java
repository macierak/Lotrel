package com.lotrel.ltserwer.reportsModule.domain.csv;

import com.lotrel.ltserwer.reportsModule.domain.report.ReportSprintResponse;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
public class CsvProjectResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = -8859256062051690395L;

    private long id;
    private String name;

    private List<ReportSprintResponse> sprints;

    private boolean active;
}
