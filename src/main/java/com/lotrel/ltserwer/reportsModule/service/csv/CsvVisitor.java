package com.lotrel.ltserwer.reportsModule.service.csv;

import com.lotrel.ltserwer.reportsModule.domain.csv.CsvProjectRequest;
import com.lotrel.ltserwer.reportsModule.domain.csv.CsvSprintRequest;
import com.lotrel.ltserwer.reportsModule.domain.csv.CsvUserTasksRequest;

public interface CsvVisitor {

    String visit(CsvSprintRequest request);
    String visit(CsvProjectRequest request);
    String visit(CsvUserTasksRequest request);
}
