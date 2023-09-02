package com.lotrel.ltserwer.projectModule.endpoint;

import com.lotrel.ltserwer.lotrelCommons.common.ApiPath;
import com.lotrel.ltserwer.projectModule.domain.dto.ProjectDto;
import com.lotrel.ltserwer.projectModule.domain.dto.SprintDto;
import com.lotrel.ltserwer.projectModule.domain.dto.TaskDto;
import com.lotrel.ltserwer.projectModule.infrastructure.mappers.ProjectToDtoMapper;
import com.lotrel.ltserwer.projectModule.infrastructure.mappers.SprintToDtoMapper;
import com.lotrel.ltserwer.projectModule.infrastructure.mappers.TaskToDtoMapper;
import com.lotrel.ltserwer.projectModule.protocol.request.project.*;
import com.lotrel.ltserwer.projectModule.service.ProjectEditService;
import com.lotrel.ltserwer.projectModule.service.ProjectInfoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Set;

@RestController
@AllArgsConstructor
public class ProjectController {

    private final ProjectInfoService projectInfoService;
    private final ProjectEditService projectEditService;

    @PostMapping(ApiPath.ProjectPath.PROJECT_CREATE)
    public ProjectDto createProject(@RequestBody CreateProjectRequest request, Principal principal) {
        request.setPrincipal(principal);
        return ProjectToDtoMapper.INSTANCE.map(projectEditService.createProject(request));
    }

    @PostMapping(ApiPath.ProjectPath.PROJECT_CLOSE)
    public void closeProject(@RequestBody CreateProjectRequest request, Principal principal) {
        request.setPrincipal(principal);
        projectEditService.createProject(request);
    }

    @GetMapping(ApiPath.ProjectPath.PROJECT_INFO)
    public ProjectDto getProjectInfo(Long projectId) {
        return ProjectToDtoMapper.INSTANCE.map(projectInfoService.getProjectInfo(projectId));
    }

    @GetMapping(ApiPath.ProjectPath.PROJECT_SPRINTS)
    public List<SprintDto> getProjectSprints(Long projectId) {
        return SprintToDtoMapper.INSTANCE.map(projectInfoService.findSprintsInProject(projectId));
    }

    @GetMapping(ApiPath.ProjectPath.PROJECT_TASKS)
    public Set<TaskDto> getTasksInProject(long projectId) {
        return TaskToDtoMapper.INSTANCE.map(projectInfoService.findTasksInProject(projectId));
    }


}
