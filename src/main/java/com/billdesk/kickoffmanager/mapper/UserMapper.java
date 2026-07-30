package com.billdesk.kickoffmanager.mapper;

import com.billdesk.kickoffmanager.dto.UserRequestDto;
import com.billdesk.kickoffmanager.dto.UserResponseDto;
import com.billdesk.kickoffmanager.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "teamId", source = "team.id")
    @Mapping(target = "teamName", source = "team.name")
    UserResponseDto toDto(User user);

    @Mapping(target = "team", ignore = true) // set manually in service after fetching by id
    @Mapping(target = "password", ignore = true) // hash it in service, don't map raw
    User toEntity(UserRequestDto userRequestDto);

    List<UserResponseDto> toDtoList(List<User> list);
}
