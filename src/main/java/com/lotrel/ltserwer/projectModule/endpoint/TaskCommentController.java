package com.lotrel.ltserwer.projectModule.endpoint;

import com.lotrel.ltserwer.lotrelCommons.common.ApiPath;
import com.lotrel.ltserwer.projectModule.protocol.request.task.comment.CommentTaskRequest;
import com.lotrel.ltserwer.projectModule.service.TaskCommentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@AllArgsConstructor
public class TaskCommentController {

    private final TaskCommentService taskCommentService;

    @PostMapping(ApiPath.TaskCommentPath.TASK_COMMENT)
    public void commentTask(@RequestBody CommentTaskRequest request, Principal principal) {
        request.setPrincipal(principal);
        taskCommentService.commentTask(request);
    }

}
