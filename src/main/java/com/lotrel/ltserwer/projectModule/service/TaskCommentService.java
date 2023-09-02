package com.lotrel.ltserwer.projectModule.service;

import com.lotrel.ltserwer.projectModule.model.Task;
import com.lotrel.ltserwer.projectModule.model.TaskComment;
import com.lotrel.ltserwer.projectModule.protocol.request.task.comment.CommentTaskRequest;
import com.lotrel.ltserwer.lotrelCommons.common.CommonEntityFinder;
import com.lotrel.ltserwer.projectModule.repository.TaskCommentRepository;
import com.lotrel.ltserwer.userModule.model.Users;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
@AllArgsConstructor
public class TaskCommentService {

    private final TaskCommentRepository taskCommentRepository;
    private final CommonEntityFinder finder;

    @Transactional
    public void commentTask(CommentTaskRequest request) {
        final Users reporter = finder.getUser(request.getPrincipal());
        final Task task = finder.getTask(request.getTaskId());
        final TaskComment comment = new TaskComment();

        comment.setComment(request.getComment());
        comment.setDate(OffsetDateTime.now());
        comment.setUser(reporter);
        comment.setDeleted(false);

        task.getComments().add(taskCommentRepository.save(comment));
    }

    @Transactional
    public void editComment(CommentTaskRequest request) {
        final Users reporter = finder.getUser(request.getPrincipal());
        final Task task = finder.getTask(request.getTaskId());
        final TaskComment comment = finder.getTaskComment(request.getTaskId());

        comment.setComment(request.getComment());
        comment.setDate(OffsetDateTime.now());
        comment.setUser(reporter);
        comment.setDeleted(false);

        task.getComments().add(taskCommentRepository.save(comment));
    }


}
