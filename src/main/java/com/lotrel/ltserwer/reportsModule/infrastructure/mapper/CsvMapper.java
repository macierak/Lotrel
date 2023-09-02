package com.lotrel.ltserwer.reportsModule.infrastructure.mapper;

import com.lotrel.ltserwer.reportsModule.domain.csv.*;
import com.lotrel.ltserwer.reportsModule.domain.report.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CsvMapper {
    CsvMapper INSTANCE = Mappers.getMapper(CsvMapper.class);

    ReportSprintRequest map(CsvSprintRequest request);
    CsvSprintResponse map(ReportSprintResponse response);

    ReportProjectRequest map(CsvProjectRequest request);
    CsvProjectResponse map(ReportProjectResponse response);

    ReportUserTasksRequest map(CsvUserTasksRequest request);
    CsvUserTasksResponse map(ReportUserTasksResponse response);
}
