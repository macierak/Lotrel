package com.lotrel.ltserwer.projectModule.service;

import com.lotrel.ltserwer.lotrelCommons.common.CommonEntityFinder;
import com.lotrel.ltserwer.lotrelCommons.common.exception.NotAuthorizedException;
import com.lotrel.ltserwer.projectModule.domain.enumeration.EditType;
import com.lotrel.ltserwer.projectModule.domain.enumeration.Roles;
import com.lotrel.ltserwer.projectModule.infrastructure.validators.ValidateSprintRequests;
import com.lotrel.ltserwer.projectModule.model.Project;
import com.lotrel.ltserwer.projectModule.model.Sprint;
import com.lotrel.ltserwer.projectModule.protocol.request.sprint.CloseSprintRequest;
import com.lotrel.ltserwer.projectModule.protocol.request.sprint.CreateSprintRequest;
import com.lotrel.ltserwer.projectModule.protocol.request.sprint.EditSprintRequest;
import com.lotrel.ltserwer.projectModule.repository.*;
import com.lotrel.ltserwer.userModule.model.Users;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Slf4j
@Service
@AllArgsConstructor
public class SprintService {

    private final SprintRepository sprintRepository;
    private ProjectEditsRepository projectEditsRepository;
    private ValidateSprintRequests validate;
    private final CommonEntityFinder finder;

    public Sprint createSprint(CreateSprintRequest request) {
        validate.request(request);

        final Users reporter = finder.getUser(request.getPrincipal());
        final Project project = finder.getProject(request.getProjectId());
        final Sprint sprint = new Sprint();

        sprint.setDescription(request.getDescription());
        sprint.setBeginDate(request.getBeginDate());
        sprint.setEndDate(request.getEndDate());
        sprint.setProject(project);

        projectEditsRepository.save(EditService.generateEditNote(reporter, project, EditType.CREATE_SPRINT, sprint, null));

        return sprintRepository.save(sprint);
    }

    public void closeSprint(CloseSprintRequest request) {
        final Users reporter = finder.getUser(request.getPrincipal());
        final Sprint sprint = finder.getSprint(request.getSprintId());

        if (!checkPermissionsForSprintManagement(reporter, sprint)) {
            throw new NotAuthorizedException();
        }

        sprint.setActive(false);
    }

    @Transactional
    public Sprint editSprintDetails(EditSprintRequest request) {
        final Users reporter = finder.getUser(request.getPrincipal());
        final Sprint sprint = finder.getSprint(request.getSprintId());
        ValidateSprintRequests.validateDateInSprintRequest(sprint, request.getBeginDate(), request.getEndDate());

        if (Objects.nonNull(request.getBeginDate())) {
            sprint.setBeginDate(request.getBeginDate());
        }

        if (Objects.nonNull(request.getEndDate())) {
            sprint.setEndDate(request.getEndDate());
        }

        if (Objects.nonNull(request.getDescription())) {
            sprint.setDescription(request.getDescription());
        }

        return sprintRepository.save(sprint);

    }

    public Sprint getSprint(Long id) {
        return finder.getSprint(id);
    }

    public List<Sprint> getSprintByDateRange(OffsetDateTime dateFrom, OffsetDateTime dateTo) {
        return sprintRepository.findAllByDatesRange(dateFrom, dateTo);
    }

    private boolean checkPermissionsForSprintManagement(Users user, Sprint sprint) {
        final Set<Roles> authorizedRoles = Set.of(Roles.PROJECT_MANAGER, Roles.MAINTAINER, Roles.OWNER);
        return authorizedRoles.contains(sprint.getProject().getUsers().get(user));
    }

}
