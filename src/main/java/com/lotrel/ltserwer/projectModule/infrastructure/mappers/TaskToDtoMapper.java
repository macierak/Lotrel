package com.lotrel.ltserwer.projectModule.infrastructure.mappers;

import com.lotrel.ltserwer.projectModule.domain.dto.TaskDto;
import com.lotrel.ltserwer.projectModule.model.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;

@Mapper
public interface TaskToDtoMapper {
    TaskToDtoMapper INSTANCE = Mappers.getMapper(TaskToDtoMapper.class);

    @Mapping(target = "user", source = "assignee")
    TaskDto map(Task source);
    Set<TaskDto> map(Set<Task> source);
    List<TaskDto> map(List<Task> source);


}
