package com.lotrel.ltserwer.projectModule.protocol.request.project;

import com.lotrel.ltserwer.lotrelCommons.common.protocol.BaseRequest;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class EditProjectOwnerRequest extends BaseRequest implements Serializable {
    @Serial
    private final static long serialVersionUID = 6423846756011686464L;

    private long projectId;

    private long ownerId;

}
