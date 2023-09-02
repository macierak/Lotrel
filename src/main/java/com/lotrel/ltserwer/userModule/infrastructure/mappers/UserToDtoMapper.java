package com.lotrel.ltserwer.userModule.infrastructure.mappers;

import com.lotrel.ltserwer.userModule.domain.dto.UserDto;
import com.lotrel.ltserwer.userModule.model.Users;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserToDtoMapper {
    UserToDtoMapper INSTANCE = Mappers.getMapper(UserToDtoMapper.class);

    UserDto map(Users users);
}
