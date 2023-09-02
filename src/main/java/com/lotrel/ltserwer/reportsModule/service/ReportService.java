package com.lotrel.ltserwer.reportsModule.service;

import com.lotrel.ltserwer.projectModule.domain.enumeration.SortDirection;
import com.lotrel.ltserwer.projectModule.domain.enumeration.TaskEditType;
import com.lotrel.ltserwer.projectModule.model.Sprint;
import com.lotrel.ltserwer.projectModule.model.Task;
import com.lotrel.ltserwer.projectModule.model.UserTaskEdits;
import com.lotrel.ltserwer.projectModule.repository.TaskRepository;
import com.lotrel.ltserwer.projectModule.repository.UserTaskEditRepository;
import com.lotrel.ltserwer.projectModule.service.ProjectInfoService;
import com.lotrel.ltserwer.projectModule.service.SprintService;
import com.lotrel.ltserwer.projectModule.service.TaskService;
import com.lotrel.ltserwer.reportsModule.domain.report.*;
import com.lotrel.ltserwer.reportsModule.infrastructure.mapper.ReportMapper;
import com.lotrel.ltserwer.userModule.model.Users;
import com.lotrel.ltserwer.userModule.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReportService {

    private final ProjectInfoService projectService;
    private final SprintService sprintService;
    private final TaskService taskService;
    private final UserService userService;
    private final TaskRepository taskRepository;
    private final UserTaskEditRepository userTaskEditRepository;

    public ReportSprintResponse createReportSprint(ReportSprintRequest request) {
        final Sprint sprint = sprintService.getSprint(request.getSprintId());
        final List<Task> taskList = taskService.getSortedTasksListWithSprint(request.getSprintId(), request.getSortBy(), request.getDirection());

        final ReportSprintResponse response = ReportMapper.INSTANCE.map(sprint);
        response.setTasks(taskList.stream().map(ReportMapper.INSTANCE::map).collect(Collectors.toList()));

        return response;
    }

    public ReportProjectResponse createReportProject(ReportProjectRequest request) {
        final List<ReportSprintResponse> sprintResponses = createReportSprintList(
                sprintService.getSprintByDateRange(request.getDateFrom(), request.getDateTo()), request);

        final ReportProjectResponse response = ReportMapper.INSTANCE.map(projectService.getProjectInfo(request.getProjectId()));
        response.setSprints(sprintResponses);
        return response;
    }

    public ReportUserTasksResponse createReportUserTasks(ReportUserTasksRequest request) {
        final ReportUserTasksResponse response = new ReportUserTasksResponse();

        final Users user = userService.getUserById(request.getUserId());
        if (Objects.isNull(user.getUserDetails()) || user.getUserDetails().getFirstName().isBlank() || user.getUserDetails().getLastName().isBlank()) {
            response.setUsername(user.getUsername());
        } else response.setUsername(user.getUserDetails().getFirstName() + " " + user.getUserDetails().getLastName());
        response.setActive(user.getAccountActive());

        final Set<Task> tasksAll = new HashSet<>(taskRepository.findAllByAssigneeIdAndDeletedIsFalse(user.getId()));
        tasksAll.addAll(userTaskEditRepository.findAllByReporterAndEditTypeAndDates(user.getId(), TaskEditType.LOGGED_TIME, request.getDateFrom(), request.getDateTo())
                .stream()
                .map(UserTaskEdits::getTask)
                .collect(Collectors.toSet()));

        List<Task> sortedTasks = getTasksSortedList(tasksAll, request.getSortBy(), request.getDirection());
        response.setTasks(sortedTasks.stream().map(ReportMapper.INSTANCE::map).toList());
        return response;
    }

    private List<Task> getTasksSortedList(Set<Task> taskSet, String orderBy, SortDirection sortDirection) {
        Set<Long> taskSkus = taskSet.stream().map(Task::getId).collect(Collectors.toSet());
        return taskService.getSortedTasksList(taskSkus, orderBy, sortDirection);
    }

    private List<ReportSprintResponse> createReportSprintList(List<Sprint> sprints, ReportProjectRequest request) {
        final List<ReportSprintResponse> responses = new ArrayList<>();

        for (Sprint sprint : sprints) {
            List<Task> tasks = taskService.getSortedTasksListWithSprint(sprint.getId(), request.getSortBy(), request.getDirection());
            final ReportSprintResponse reportSprintResponse = ReportMapper.INSTANCE.map(sprint);
            reportSprintResponse.setTasks(tasks.stream()
                    .filter(task -> task.getCreationDate().isAfter(request.getDateFrom()) && task.getCreationDate().isBefore(request.getDateTo()))
                    .filter(task -> request.getTaskWorkflows().contains(task.getCurrentWorkflow()))
                    .filter(task -> request.getTaskTypes().contains(task.getUrgencyType()))
                    .map(ReportMapper.INSTANCE::map)
                    .toList());
            responses.add(reportSprintResponse);
        }

        return responses;
    }
}
