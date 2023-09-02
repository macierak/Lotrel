package com.lotrel.ltserwer.projectModule.infrastructure.mappers;

import com.lotrel.ltserwer.projectModule.domain.dto.SprintDto;
import com.lotrel.ltserwer.projectModule.model.Sprint;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SprintToDtoMapper {
    SprintToDtoMapper INSTANCE = Mappers.getMapper(SprintToDtoMapper.class);

    SprintDto map(Sprint source);

    List<SprintDto> map(List<Sprint> source);
}
