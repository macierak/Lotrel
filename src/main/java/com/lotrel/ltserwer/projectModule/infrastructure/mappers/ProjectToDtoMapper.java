package com.lotrel.ltserwer.projectModule.infrastructure.mappers;

import com.lotrel.ltserwer.projectModule.domain.dto.ProjectDto;
import com.lotrel.ltserwer.projectModule.model.Project;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProjectToDtoMapper {
    ProjectToDtoMapper INSTANCE = Mappers.getMapper(ProjectToDtoMapper.class);

    ProjectDto map (Project source);
}
