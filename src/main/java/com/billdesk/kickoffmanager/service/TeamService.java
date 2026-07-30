package com.billdesk.kickoffmanager.service;


import com.billdesk.kickoffmanager.dto.TeamResponseDto;

import java.util.List;

public interface TeamService {
    public TeamResponseDto createTeam(String name,Long tournament_id);

    public TeamResponseDto getTeamInfo(Long team_id);

    public List<TeamResponseDto> getAllTeams();
}
