package com.billdesk.kickoffmanager.mapper;

import com.billdesk.kickoffmanager.dto.UserRequestDto;
import com.billdesk.kickoffmanager.dto.UserResponseDto;
import com.billdesk.kickoffmanager.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponseDto toDto(User user);

    User toEntity(UserRequestDto userRequestDto);

    List<UserResponseDto> toDtoList(List<User> list);
}
