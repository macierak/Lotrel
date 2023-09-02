package com.lotrel.ltserwer.projectModule.protocol.request.project;

import com.lotrel.ltserwer.projectModule.domain.enumeration.Roles;
import com.lotrel.ltserwer.lotrelCommons.common.protocol.BaseRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class EditUsersInProjectRequest extends BaseRequest {
    private Long projectId;
    private Map<Long, Roles> userMap;
}
