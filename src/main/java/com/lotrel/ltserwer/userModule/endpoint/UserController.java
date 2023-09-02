package com.lotrel.ltserwer.userModule.endpoint;

import com.lotrel.ltserwer.lotrelCommons.common.ApiPath;
import com.lotrel.ltserwer.projectModule.domain.dto.ProjectDto;
import com.lotrel.ltserwer.projectModule.infrastructure.mappers.ProjectToDtoMapper;
import com.lotrel.ltserwer.userModule.domain.dto.UserDto;
import com.lotrel.ltserwer.userModule.infrastructure.mappers.UserToDtoMapper;
import com.lotrel.ltserwer.userModule.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Set;
import java.util.stream.Collectors;


@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(ApiPath.UserPath.USER_CURRENT_INFO)
    public UserDto getCurrentUserInfo(Principal principal) {
        return UserToDtoMapper.INSTANCE.map(userService.getUserByPrincipal(principal));
    }

    @GetMapping(ApiPath.UserPath.USER_INFO)
    public UserDto getUserInfo(Long userId) {
        return UserToDtoMapper.INSTANCE.map(userService.getUserById(userId));
    }

    @GetMapping(ApiPath.UserPath.USER_PROJECTS)
    public Set<ProjectDto> getUserProjects(Long userId) {
        return userService.getUserProjects(userId).stream()
                .map(ProjectToDtoMapper.INSTANCE::map)
                .collect(Collectors.toSet());
    }

    @GetMapping(ApiPath.UserPath.USER_ALL)
    public Set<UserDto> getAllUsers() {
        return userService.getAllUsers().stream().map(UserToDtoMapper.INSTANCE::map).collect(Collectors.toSet());
    }

}
