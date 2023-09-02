package com.lotrel.ltserwer.userModule.domain.dto;

import com.lotrel.ltserwer.projectModule.domain.dto.ProjectDto;
import com.lotrel.ltserwer.projectModule.domain.dto.TaskDto;
import lombok.Data;

import java.util.Set;

@Data
public class UserDetailsDto {
    private String firstName;
    private String lastName;
    private String company;
    private String position;
    private String imageUrl;
    private Set<ProjectDto> projects;
    private Set<TaskDto> lastTasks;
}
