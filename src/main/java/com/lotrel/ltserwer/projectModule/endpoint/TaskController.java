package com.lotrel.ltserwer.projectModule.endpoint;

import com.lotrel.ltserwer.lotrelCommons.common.ApiPath;
import com.lotrel.ltserwer.projectModule.domain.dto.TaskDto;
import com.lotrel.ltserwer.projectModule.infrastructure.mappers.TaskToDtoMapper;
import com.lotrel.ltserwer.projectModule.protocol.request.project.EditTaskRequest;
import com.lotrel.ltserwer.projectModule.protocol.request.task.AssignTaskToUserRequest;
import com.lotrel.ltserwer.projectModule.protocol.request.task.CreateTaskRequest;
import com.lotrel.ltserwer.projectModule.protocol.request.task.LogTimeRequest;
import com.lotrel.ltserwer.projectModule.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@AllArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping(ApiPath.TaskPath.TASK_CREATE)
    public TaskDto createNewTask(@RequestBody CreateTaskRequest request, Principal principal) {
        request.setPrincipal(principal);
        return TaskToDtoMapper.INSTANCE.map(taskService.createNewTask(request));
    }

    @PostMapping(ApiPath.TaskPath.TASK_ASSIGN)
    public void assignTaskToUser(@RequestBody AssignTaskToUserRequest request, Principal principal) {
        request.setPrincipal(principal);
        taskService.assignTaskToUser(request);
    }

    @GetMapping(ApiPath.TaskPath.TASK_INFO)
    public TaskDto getTaskInfo(Long taskId) {
        return taskService.getTaskInfo(taskId);
    }

    @PostMapping(ApiPath.TaskPath.TASK_LOG_TIME)
    public void logTime(@RequestBody LogTimeRequest request, Principal principal) {
        request.setPrincipal(principal);
        taskService.logTimeInTask(request);
    }

    @PostMapping(ApiPath.TaskPath.TASK_EDIT)
    public TaskDto editTask(@RequestBody EditTaskRequest request, Principal principal) {
        request.setPrincipal(principal);
        return taskService.editTask(request);
    }

}
