package com.billdesk.kickoffmanager.mapper;


import com.billdesk.kickoffmanager.dto.TournamentResponseDto;
import com.billdesk.kickoffmanager.entity.Tournament;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TournamentMapper {
    TournamentResponseDto toDto(Tournament tournament);

    Tournament toEntity(TournamentResponseDto dto);

    List<TournamentResponseDto> toDtoList(List<Tournament> tournaments);
}
