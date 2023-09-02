package com.lotrel.ltserwer.projectModule.service;

import com.lotrel.ltserwer.lotrelCommons.common.CommonEntityFinder;
import com.lotrel.ltserwer.projectModule.domain.dto.TaskDto;
import com.lotrel.ltserwer.projectModule.domain.enumeration.*;
import com.lotrel.ltserwer.projectModule.infrastructure.mappers.TaskToDtoMapper;
import com.lotrel.ltserwer.projectModule.infrastructure.validators.ValidateTaskRequest;
import com.lotrel.ltserwer.projectModule.model.Sprint;
import com.lotrel.ltserwer.projectModule.model.Task;
import com.lotrel.ltserwer.projectModule.protocol.request.project.EditTaskRequest;
import com.lotrel.ltserwer.projectModule.protocol.request.task.AssignTaskToUserRequest;
import com.lotrel.ltserwer.projectModule.protocol.request.task.CreateTaskRequest;
import com.lotrel.ltserwer.projectModule.protocol.request.task.LogTimeRequest;
import com.lotrel.ltserwer.projectModule.repository.*;
import com.lotrel.ltserwer.reportsModule.domain.mail.MailAssignTaskRequest;
import com.lotrel.ltserwer.reportsModule.domain.mail.MailNewTaskRequest;
import com.lotrel.ltserwer.reportsModule.service.mail.MailVisitor;
import com.lotrel.ltserwer.userModule.model.Users;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;


@Slf4j
@Service
@AllArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    private final UserTaskEditRepository userTaskEditRepository;

    private final CommonEntityFinder finder;

    private final MailVisitor mailVisitor;


    private final ValidateTaskRequest validate;

    @Transactional
    public Task createNewTask(CreateTaskRequest req) {
        validate.request(req);

        final Users reporter = finder.getUser(req.getPrincipal());
        final Sprint sprint;
        if (Objects.nonNull(req.getSprintId())) {
            sprint = finder.getSprint(req.getSprintId());
        } else {
            sprint = finder.getProjectByKey(req.getProjectKey()).getBacklogSprint();
        }

        final Task task = new Task();
        task.setDescription(req.getDescription());
        task.setReporter(reporter);
        task.setTaskId(req.getProjectKey() + "-" + taskRepository.save(task).getId());
        task.setTaskExternalUrl(PresetValues.TASK_BASE_URL.value + task.getTaskId());
        task.setTaskName(req.getTaskName());
        task.setSprint(sprint);
        task.setCurrentWorkflow(Workflow.OPEN);
        task.setUrgencyType(UrgencyType.MINOR);
        task.setAssignee(reporter);
        task.setCreationDate(OffsetDateTime.now());

        final MailNewTaskRequest mail = new MailNewTaskRequest(
                reporter.getEmail(), task.getTaskName(), task.getTaskId(), sprint.getProject().getProjectName(), sprint.getDescription());
        mail.accept(mailVisitor);

        return task;
    }

    @Transactional
    public void assignTaskToUser(AssignTaskToUserRequest req) {
        final Users reporter = finder.getUser(req.getPrincipal());
        final Task task = finder.getTask(req.getTaskId());

        final Users oldAssignee = task.getAssignee();

        final Users newAssignee = finder.getUser(req.getUserId());

        userTaskEditRepository.save(
                EditService.generateEditNote(reporter, task, TaskEditType.EDIT_ASSIGNEE, oldAssignee, newAssignee));

        task.setAssignee(newAssignee);

        String newUsername;
        if (Objects.isNull(newAssignee.getUserDetails()) || newAssignee.getUserDetails().getFirstName().isBlank() || newAssignee.getUserDetails().getLastName().isBlank()) {
            newUsername = newAssignee.getUsername();
        } else {
            newUsername = newAssignee.getUserDetails().getFirstName() + " " + newAssignee.getUserDetails().getLastName();
        }
        final MailAssignTaskRequest mail = new MailAssignTaskRequest(
                List.of(reporter.getEmail(), oldAssignee.getEmail(), newAssignee.getEmail(), task.getReporter().getEmail()),
                newUsername,
                task.getTaskName(),
                task.getTaskId());
        mail.accept(mailVisitor);
    }

    @Transactional
    public TaskDto editTask(EditTaskRequest req) {
        final Users reporter = finder.getUser(req.getPrincipal());
        final Task task = finder.getTask(req.getTaskId());

        if (Objects.nonNull(req.getTaskName())) {
            userTaskEditRepository.save(
                    EditService.generateEditNote(reporter, task, TaskEditType.EDIT_TASK,
                    req.getTaskName(), task.getTaskName())
            );

            task.setTaskName(req.getTaskName());
        }

        if (Objects.nonNull(req.getTaskDescription())) {
            userTaskEditRepository.save(
                    EditService.generateEditNote(reporter, task, TaskEditType.EDIT_TASK,
                            req.getTaskDescription(), task.getDescription())
            );

            task.setDescription(req.getTaskDescription());
        }

        if (Objects.nonNull(req.getWorkflow())) {
            task.setCurrentWorkflow(req.getWorkflow());
        }

        if (Objects.nonNull(req.getUrgencyType())) {
            task.setUrgencyType(req.getUrgencyType());
        }

        if (Objects.nonNull(req.getSprintId())) {
            task.setSprint(finder.getSprint(req.getSprintId()));
        }

        if (Objects.nonNull(req.getUserId())) {
            task.setAssignee(finder.getUser(req.getUserId()));
        }

        return TaskToDtoMapper.INSTANCE.map(task);
    }

    public TaskDto getTaskInfo(Long taskId) {
        return TaskToDtoMapper.INSTANCE.map(finder.getTask(taskId));
    }

    public List<Task> getSortedTasksList(Set<Long> taskSkus, String orderBy, SortDirection sortDirection) {
        if (sortDirection == SortDirection.ASC) {
            return taskRepository.findAllByTaskIdAndOrderBy(taskSkus, Sort.by(Sort.Direction.ASC, orderBy));
        } else {
            return taskRepository.findAllByTaskIdAndOrderBy(taskSkus, Sort.by(Sort.Direction.DESC, orderBy));
        }
    }

    public List<Task> getSortedTasksListWithSprint(long sprintId, String orderBy, SortDirection sortDirection) {
        if (sortDirection == SortDirection.ASC) {
            return taskRepository.findAllBySprintAndOrderBy(sprintId, Sort.by(Sort.Direction.ASC, orderBy));
        } else {
            return taskRepository.findAllBySprintAndOrderBy(sprintId, Sort.by(Sort.Direction.DESC, orderBy));
        }
    }

    @Transactional
    public void logTimeInTask(LogTimeRequest request) {
        final Task task = finder.getTask(request.getTaskId());
        final Users user = finder.getUser(request.getPrincipal());
        final Integer prevTime = task.getTotalLoggedTime();
        task.setTotalLoggedTime(task.getTotalLoggedTime() + request.getTimeInMinutes());

        if (Objects.nonNull(task.getRemainingTime())) {
            task.setRemainingTime(task.getRemainingTime() - request.getTimeInMinutes());
        }

        userTaskEditRepository.save(
                EditService.generateEditNote(user, task, TaskEditType.LOGGED_TIME, request.getTimeInMinutes(), prevTime)
        );
    }

}
