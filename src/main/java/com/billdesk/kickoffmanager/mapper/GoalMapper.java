package com.billdesk.kickoffmanager.mapper;

import com.billdesk.kickoffmanager.dto.GoalResponseDto;
import com.billdesk.kickoffmanager.entity.Goal;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GoalMapper {

    @Mapping(target = "fixtureId", source = "fixture.id")
    @Mapping(target = "scorer", source = "scorer.name")
    @Mapping(target = "assistBy", source = "assister.name")
    GoalResponseDto toDto(Goal goal);

    List<GoalResponseDto> toListDto(List<Goal> goals);

}
