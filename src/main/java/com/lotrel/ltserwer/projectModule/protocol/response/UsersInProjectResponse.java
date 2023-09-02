package com.lotrel.ltserwer.projectModule.protocol.response;

import com.lotrel.ltserwer.projectModule.domain.enumeration.Roles;
import com.lotrel.ltserwer.userModule.domain.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class UsersInProjectResponse {
    Map<UserDto, Roles> usersRolesMap;
}
