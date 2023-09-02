package com.lotrel.ltserwer.projectModule.service;

import com.lotrel.ltserwer.projectModule.domain.enumeration.Roles;
import com.lotrel.ltserwer.projectModule.model.Project;
import com.lotrel.ltserwer.projectModule.model.Sprint;
import com.lotrel.ltserwer.projectModule.model.Task;
import com.lotrel.ltserwer.projectModule.protocol.request.project.FindUsersInProjectSpecificationRequest;
import com.lotrel.ltserwer.lotrelCommons.common.CommonEntityFinder;
import com.lotrel.ltserwer.projectModule.repository.SprintRepository;
import com.lotrel.ltserwer.userModule.model.Users;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class ProjectInfoService {
    private final SprintRepository sprintRepository;
    private final CommonEntityFinder finder;

    public Project getProjectInfo(Long projectId) {
        return finder.getProject(projectId);
    }

    public Set<Task> findTasksInProject(Long projectId) {
        final Set<Task> result = new HashSet<>();
        findSprintsInProject(projectId).forEach(s -> {
            result.addAll(finder.getTaskBySprint(s));
        });
        return result;
    }

    public Map<Users, Roles> findUsersInProject(Long projectId) {
        return finder.getProject(projectId).getUsers();
    }

    public List<Sprint> findSprintsInProject(Long projectId) {
        return sprintRepository.findAllByProjectIdAndDeletedIsFalse(projectId);
    }

    public Map<Users, Roles> findUsersInProjectSpecification(FindUsersInProjectSpecificationRequest request) {
        final Map<Users, Roles> usersInProject = finder.getProject(request.getProjectId()).getUsers();

        return usersInProject.entrySet().stream()
                .filter(en -> en.getKey().getUsername().contains(request.getValue())
                        ||  en.getValue().toString().equalsIgnoreCase(request.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));


    }

}
