package com.lotrel.ltserwer.projectModule.infrastructure.validators;

import com.lotrel.ltserwer.projectModule.infrastructure.exception.KeyExistsException;
import com.lotrel.ltserwer.projectModule.protocol.request.project.CreateProjectRequest;
import com.lotrel.ltserwer.projectModule.repository.ProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ValidateProjectRequests {

    private final ProjectRepository projectRepository;

    public void request(CreateProjectRequest req) {
        validateCreateProjectRequest(req);
    }

    private void validateCreateProjectRequest(CreateProjectRequest req) {
        if (projectRepository.findFirstByProjectKey(req.getProjectKey()).isEmpty()) {
            throw new KeyExistsException(req.getProjectKey());
        }
    }
}
