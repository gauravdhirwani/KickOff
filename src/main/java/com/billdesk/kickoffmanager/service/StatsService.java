package com.billdesk.kickoffmanager.service;

import com.billdesk.kickoffmanager.dto.PlayerStatDto;
import com.billdesk.kickoffmanager.dto.TeamStandingDto;

import java.util.List;

public interface StatsService {
    List<PlayerStatDto> getTopScorers();
    List<PlayerStatDto> getTopAssisters();
    List<TeamStandingDto> getLeaderboard();
}