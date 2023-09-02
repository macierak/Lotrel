package com.lotrel.ltserwer.projectModule.domain.dto;

import com.lotrel.ltserwer.userModule.domain.dto.UserDto;
import lombok.Data;

@Data
public class ProjectDto {
    private Long id;
    private String projectName;
    private String projectKey;
    private UserDto owner;
}
