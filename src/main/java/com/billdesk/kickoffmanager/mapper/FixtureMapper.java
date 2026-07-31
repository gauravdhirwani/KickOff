package com.billdesk.kickoffmanager.mapper;

import com.billdesk.kickoffmanager.dto.FixtureResponseDto;
import com.billdesk.kickoffmanager.entity.Fixture;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FixtureMapper {

    @Mapping(target = "tournamentName", source = "tournament.name")
    @Mapping(target = "homeTeam", source = "homeTeam.name")
    @Mapping(target = "awayTeam", source = "awayTeam.name")
    @Mapping(target = "homeTeamId", source = "homeTeam.id")
    @Mapping(target = "awayTeamId", source = "awayTeam.id")
    FixtureResponseDto toDto(Fixture fixture);

    List<FixtureResponseDto> toListDto(List<Fixture> fixtures);
}
