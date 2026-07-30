package com.billdesk.kickoffmanager.mapper;

import com.billdesk.kickoffmanager.dto.TeamResponseDto;
import com.billdesk.kickoffmanager.entity.Team;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TeamMapper {
    List<TeamResponseDto> toDtoList(List<Team> teams);

     Team toEntity(TeamResponseDto dto);

     @Mapping(target = "tournamentId", source = "tournament.id")
    TeamResponseDto toDto(Team team);
}
