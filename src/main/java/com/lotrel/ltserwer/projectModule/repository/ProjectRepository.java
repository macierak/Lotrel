package com.lotrel.ltserwer.projectModule.repository;

import com.lotrel.ltserwer.projectModule.domain.enumeration.Roles;
import com.lotrel.ltserwer.projectModule.model.Project;
import com.lotrel.ltserwer.userModule.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    Optional<Project> findByIdAndActiveTrue(long id);
    Optional<Project> findFirstByProjectKey(String projectKey);

}
