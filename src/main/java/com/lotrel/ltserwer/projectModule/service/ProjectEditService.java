package com.lotrel.ltserwer.projectModule.service;

import com.lotrel.ltserwer.lotrelCommons.common.exception.NotAuthorizedException;
import com.lotrel.ltserwer.projectModule.domain.enumeration.EditType;
import com.lotrel.ltserwer.projectModule.domain.enumeration.Roles;
import com.lotrel.ltserwer.projectModule.model.Project;
import com.lotrel.ltserwer.projectModule.model.Sprint;
import com.lotrel.ltserwer.projectModule.protocol.request.project.AddUserToProjectRequest;
import com.lotrel.ltserwer.projectModule.protocol.request.project.CreateProjectRequest;
import com.lotrel.ltserwer.projectModule.protocol.request.project.EditProjectOwnerRequest;
import com.lotrel.ltserwer.projectModule.protocol.request.project.EditUsersInProjectRequest;
import com.lotrel.ltserwer.lotrelCommons.common.CommonEntityFinder;
import com.lotrel.ltserwer.projectModule.repository.ProjectEditsRepository;
import com.lotrel.ltserwer.projectModule.repository.ProjectRepository;
import com.lotrel.ltserwer.projectModule.repository.SprintRepository;
import com.lotrel.ltserwer.userModule.model.Users;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class ProjectEditService {

    private final ProjectRepository projectRepository;
    private final CommonEntityFinder finder;
    private final SprintRepository sprintRepository;
    private final ProjectEditsRepository projectEditsRepository;

    @Transactional
    public Project createProject(CreateProjectRequest request) {
        log.info("Generating new project with request {}", request);
        final Users user = finder.getUser(request.getPrincipal());
        final Project project = new Project();
        final Sprint backlog = new Sprint();
        backlog.setDescription("Backlog " + request.getProjectKey());

        project.setBacklogSprint(sprintRepository.save(backlog));
        project.setProjectKey(request.getProjectKey());
        project.setProjectName(request.getProjectName());
        project.setOwner(user);
        project.setUsers(new HashMap<>());
        project.getUsers().put(user, Roles.OWNER);
        project.setActive(true);

        projectRepository.save(project);

        projectEditsRepository.save(EditService.generateEditNote(user, project, EditType.CREATE_PROJECT, project, null));


        backlog.setProject(project);
        log.info("Generated new project: {} Persisting changes to db...", project);
        return projectRepository.save(project);
    }

    @Transactional
    public Project addUserToProject(AddUserToProjectRequest request) {
        log.info("Adding user with id {} to project with id {}", request.getUserId(), request.getProjectId() );

        final Users reporter = finder.getUser(request.getPrincipal());
        final Users newUser = finder.getUser(request.getUserId());
        final Project project = finder.getProject(request.getProjectId());
        projectEditsRepository.save(EditService.generateEditNote(reporter, project, EditType.ADD_USER, newUser, null));

        project.getUsers().put(newUser, request.getRole());

        return projectRepository.save(project);
    }

    @Transactional
    public void assignRoleToUser(AddUserToProjectRequest request) {

        //final Users reporter = finder.getUser(request.getPrincipal());
        final Users user = finder.getUser(request.getUserId());
        final Project project = finder.getProject(request.getProjectId());

        final Roles role = project.getUsers().get(user);

        log.info("Editing user {} roles from {} to {}", user, role, request.getRole());

        //projectEditsRepository.save(
        //        EditService.generateEditNote(reporter, project, EditType.CHANGE_USER_ROLE, request.getRole(), role)
        //);

        project.getUsers().replace(user, request.getRole());

    }

    @Transactional
    public Project editProjectOwner(EditProjectOwnerRequest request) {
        final Users reporter = finder.getUser(request.getPrincipal());
        final Users user = finder.getUser(request.getOwnerId());
        final Project project = finder.getProject(request.getProjectId());

        if (!project.getOwner().equals(reporter)) {
            throw new NotAuthorizedException();
        }

        project.setOwner(user);

        projectEditsRepository.save(
                EditService.generateEditNote(reporter, project, EditType.CHANGE_OWNER, user, reporter)
        );

        return projectRepository.save(project);
    }

    @Transactional
    public Project editUsersInProject(EditUsersInProjectRequest request) {
        final Users reporter = finder.getUser(request.getPrincipal());
        final Project project = finder.getProject(request.getProjectId());
        final Map<Users, Roles> mapPrev = project.getUsers();

        if (!checkPermissionsForUserManagement(reporter, mapPrev)) {
            throw new NotAuthorizedException();
        }

        final Map<Users, Roles> map = request.getUserMap().entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(
                        en -> finder.getUser(en.getKey()), Map.Entry::getValue, (en1, en2) -> en1, LinkedHashMap::new)
                );

        project.setUsers(map);

        projectRepository.save(project);

        projectEditsRepository.save(
                EditService.generateEditNote(reporter, project, EditType.MANAGE_USERS, map, mapPrev)
        );

        return project;
    }

    private boolean checkPermissionsForUserManagement(Users user, Map<Users, Roles> map) {
        final Set<Roles> authorizedRoles = Set.of(Roles.PROJECT_MANAGER, Roles.MAINTAINER, Roles.OWNER);
        return authorizedRoles.contains(map.get(user));
    }

}
