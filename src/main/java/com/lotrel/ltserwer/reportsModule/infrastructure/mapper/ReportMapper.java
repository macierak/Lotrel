package com.lotrel.ltserwer.reportsModule.infrastructure.mapper;

import com.lotrel.ltserwer.projectModule.model.Project;
import com.lotrel.ltserwer.projectModule.model.Sprint;
import com.lotrel.ltserwer.projectModule.model.Task;
import com.lotrel.ltserwer.reportsModule.domain.report.ReportProjectResponse;
import com.lotrel.ltserwer.reportsModule.domain.report.ReportSprintResponse;
import com.lotrel.ltserwer.reportsModule.domain.report.ReportTaskResponse;
import com.lotrel.ltserwer.userModule.model.Users;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.Objects;

@Mapper
public interface ReportMapper {
    ReportMapper INSTANCE = Mappers.getMapper(ReportMapper.class);

    @Mapping(target = "assignee", source = "assignee", qualifiedByName = "mapAssigneeUserName")
    ReportTaskResponse map(Task task);

    @Mapping(target = "tasks", ignore = true)
    ReportSprintResponse map(Sprint sprint);

    @Mapping(target = "sprints", ignore = true)
    @Mapping(target = "name", source = "projectName")
    ReportProjectResponse map(Project project);

    @Named("mapAssigneeUserName")
    default String mapAssigneeUserName(Users user) {
        if (Objects.isNull(user)) return "Unassigned";
        if (Objects.isNull(user.getUserDetails()) || user.getUserDetails().getFirstName().isBlank() || user.getUserDetails().getLastName().isBlank()) {
            return user.getUsername();
        }
        return user.getUserDetails().getFirstName() + " " + user.getUserDetails().getLastName();
    }
}
