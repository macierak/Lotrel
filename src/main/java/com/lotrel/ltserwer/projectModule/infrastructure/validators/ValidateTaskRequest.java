package com.lotrel.ltserwer.projectModule.infrastructure.validators;

import com.lotrel.ltserwer.lotrelCommons.common.exception.EntityNotFoundException;
import com.lotrel.ltserwer.projectModule.model.Project;
import com.lotrel.ltserwer.projectModule.model.Sprint;
import com.lotrel.ltserwer.projectModule.protocol.request.task.CreateTaskRequest;
import com.lotrel.ltserwer.projectModule.repository.ProjectRepository;
import com.lotrel.ltserwer.projectModule.repository.SprintRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ValidateTaskRequest {

    private ProjectRepository projectRepository;

    private SprintRepository sprintRepository;

    public void request(CreateTaskRequest request) {
        projectRepository.findFirstByProjectKey(request.getProjectKey())
                .orElseThrow(() -> new EntityNotFoundException(Project.class));

        /*sprintRepository.findById(request.getSprintId())
                .orElseThrow(() -> new EntityNotFoundException(Sprint.class));
        */
    }

}
