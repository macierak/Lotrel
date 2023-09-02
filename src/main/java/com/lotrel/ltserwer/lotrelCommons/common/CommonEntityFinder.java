package com.lotrel.ltserwer.lotrelCommons.common;

import com.lotrel.ltserwer.lotrelCommons.common.exception.EntityNotFoundException;
import com.lotrel.ltserwer.projectModule.model.*;
import com.lotrel.ltserwer.projectModule.repository.*;
import com.lotrel.ltserwer.userModule.model.Users;
import com.lotrel.ltserwer.userModule.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@AllArgsConstructor
public class CommonEntityFinder {

    private final ProjectEditsRepository projectEditsRepository;
    private final ProjectRepository projectRepository;
    private final SprintRepository sprintRepository;
    private final TaskCommentRepository taskCommentRepository;
    private final TaskRepository taskRepository;
    private final UserTaskEditRepository userTaskEditRepository;
    private final UserRepository userRepository;


    public Project getProject(Long id) {
        return projectRepository.findByIdAndActiveTrue(id)
                .orElseThrow(() -> new EntityNotFoundException(Project.class));
    }
    public Project getProjectByKey(String key) {
        return projectRepository.findFirstByProjectKey(key)
                .orElseThrow(() -> new EntityNotFoundException(Project.class));
    }
    public ProjectEdits getProjectEdits(Long id) {
        return projectEditsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ProjectEdits.class));
    }
    public Sprint getSprint(Long id) {
        return sprintRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new EntityNotFoundException(Sprint.class));
    }

    public Task getTask(Long id) {
        return taskRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new EntityNotFoundException(Task.class));
    }

    public List<Task> getTaskBySprint(Sprint sprint) {
        return taskRepository.findAllBySprintAndDeletedFalse(sprint, Sort.unsorted());
    }

    public TaskComment getTaskComment(Long id) {
        return taskCommentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(TaskComment.class));
    }

    public UserTaskEdits getUserTaskEdits(Long id) {
        return userTaskEditRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(UserTaskEdits.class));
    }

    public Users getUser(Long id) {
        return userRepository.findByIdAndAccountActiveTrue(id)
                .orElseThrow(() -> new EntityNotFoundException(Users.class));
    }
    public Users getUser(Principal principal) {
        return userRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new EntityNotFoundException(Users.class));
    }

}
