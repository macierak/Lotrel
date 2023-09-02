package com.lotrel.ltserwer.userModule.service;

import com.lotrel.ltserwer.authModule.infrastructure.exception.ResourceNotFoundException;
import com.lotrel.ltserwer.authModule.infrastructure.oauth2.UserPrincipal;
import com.lotrel.ltserwer.lotrelCommons.common.CommonEntityFinder;
import com.lotrel.ltserwer.projectModule.model.Project;
import com.lotrel.ltserwer.projectModule.repository.ProjectRepository;
import com.lotrel.ltserwer.userModule.model.Users;
import com.lotrel.ltserwer.userModule.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final ProjectRepository projectRepository;

    private final CommonEntityFinder finder;

    public UserPrincipal loadUserById(Long id) {
        Users user = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", id)
        );

        return UserPrincipal.create(user);
    }

    public Users getUserByPrincipal(Principal principal) {
        return finder.getUser(principal);
    }

    public Users getUserById(Long id) {
        return finder.getUser(id);
    }

    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    public Set<Project> getUserProjects(Long userId) {
        final Users user = finder.getUser(userId);
        final List<Project> projects = projectRepository.findAll();
        return projects.stream()
                .filter(p -> p.getUsers().containsKey(user))
                .collect(Collectors.toSet());
    }
}
