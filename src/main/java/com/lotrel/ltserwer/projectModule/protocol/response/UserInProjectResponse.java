package com.lotrel.ltserwer.projectModule.protocol.response;

import com.lotrel.ltserwer.projectModule.domain.enumeration.Roles;
import com.lotrel.ltserwer.userModule.domain.dto.UserDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserInProjectResponse {
    private UserDto userDto;
    private Roles role;
}
