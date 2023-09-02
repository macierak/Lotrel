package com.lotrel.ltserwer.projectModule.endpoint;

import com.lotrel.ltserwer.lotrelCommons.common.ApiPath;
import com.lotrel.ltserwer.projectModule.domain.enumeration.Roles;
import com.lotrel.ltserwer.projectModule.model.Project;
import com.lotrel.ltserwer.projectModule.protocol.request.project.AddUserToProjectRequest;
import com.lotrel.ltserwer.projectModule.protocol.request.project.EditProjectOwnerRequest;
import com.lotrel.ltserwer.projectModule.protocol.request.project.EditUsersInProjectRequest;
import com.lotrel.ltserwer.projectModule.protocol.request.project.FindUsersInProjectSpecificationRequest;
import com.lotrel.ltserwer.projectModule.protocol.response.UserInProjectResponse;
import com.lotrel.ltserwer.projectModule.service.ProjectEditService;
import com.lotrel.ltserwer.projectModule.service.ProjectInfoService;
import com.lotrel.ltserwer.userModule.domain.dto.UserDto;
import com.lotrel.ltserwer.userModule.infrastructure.mappers.UserToDtoMapper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class ProjectUserController {

    private final ProjectInfoService projectInfoService;

    private final ProjectEditService projectEditService;

    @PostMapping(ApiPath.ProjectPath.PROJECT_ROLE_ASSIGN)
    public void assignRoleToUser(@RequestBody AddUserToProjectRequest request, Principal principal) {
        request.setPrincipal(principal);
        projectEditService.assignRoleToUser(request);
    }

    @PostMapping(ApiPath.ProjectPath.PROJECT_ADD_USER)
    public Project addUserToProject(@RequestBody AddUserToProjectRequest request, Principal principal) {
        request.setPrincipal(principal);
        return projectEditService.addUserToProject(request);
    }

    @PostMapping(ApiPath.ProjectPath.PROJECT_EDIT_OWNER)
    public Project editProjectOwner(@RequestBody EditProjectOwnerRequest request, Principal principal) {
        request.setPrincipal(principal);
        return projectEditService.editProjectOwner(request);
    }

    @PutMapping(ApiPath.ProjectPath.PROJECT_USERS)
    public Set<UserInProjectResponse> manageUsersInProject(
            @RequestBody EditUsersInProjectRequest request, Principal principal
    ) {
        request.setPrincipal(principal);
        return projectEditService.editUsersInProject(request).getUsers().entrySet().stream().map(en -> UserInProjectResponse.builder()
                        .userDto(UserToDtoMapper.INSTANCE.map(en.getKey()))
                        .role(en.getValue())
                        .build())
                .collect(Collectors.toSet());
    }

    @GetMapping(ApiPath.ProjectPath.PROJECT_USERS_SEARCH)
    public Set<UserInProjectResponse> getUsersInProject(@RequestBody FindUsersInProjectSpecificationRequest req) {
        return projectInfoService.findUsersInProjectSpecification(req).entrySet().stream().map(en -> UserInProjectResponse.builder()
                        .userDto(UserToDtoMapper.INSTANCE.map(en.getKey()))
                        .role(en.getValue())
                        .build())
                .collect(Collectors.toSet());
    }

    @GetMapping(ApiPath.ProjectPath.PROJECT_USERS)
    public Set<UserInProjectResponse> getUsersInProject(Long projectId) {
        return projectInfoService.findUsersInProject(projectId).entrySet().stream()
                .map(en -> UserInProjectResponse.builder()
                        .userDto(UserToDtoMapper.INSTANCE.map(en.getKey()))
                        .role(en.getValue())
                        .build())
                .collect(Collectors.toSet());
    }

}
