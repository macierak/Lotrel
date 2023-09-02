package com.lotrel.ltserwer.projectModule.protocol.request.project;

import com.lotrel.ltserwer.projectModule.domain.enumeration.Roles;
import com.lotrel.ltserwer.lotrelCommons.common.protocol.BaseRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddUserToProjectRequest extends BaseRequest {
    private Long userId;
    private Long projectId;
    private Roles role;

}
