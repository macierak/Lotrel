package com.lotrel.ltserwer.projectModule.protocol.request.task.comment;

import com.lotrel.ltserwer.lotrelCommons.common.protocol.BaseRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentTaskRequest extends BaseRequest {
    private String comment;
    private Long taskId;
    private Long commentId;
}
