package com.billdesk.kickoffmanager.serviceImpl;

import com.billdesk.kickoffmanager.dto.TeamResponseDto;
import com.billdesk.kickoffmanager.entity.Team;
import com.billdesk.kickoffmanager.entity.Tournament;
import com.billdesk.kickoffmanager.mapper.TeamMapper;
import com.billdesk.kickoffmanager.repository.TeamRepository;
import com.billdesk.kickoffmanager.repository.TournamentRepository;
import com.billdesk.kickoffmanager.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {

    private final TeamMapper teamMapper;
    private final TeamRepository teamRepository;
    private final TournamentRepository tournamentRepository;

    @Override
    public TeamResponseDto createTeam(String name, Long tournament_id) {
        Tournament tournament = tournamentRepository.findById(tournament_id)
                .orElseThrow(()->
                        new RuntimeException("Tourney not found!"));

        Team team = new Team();
        team.setName(name);
        team.setTournament(tournament);

        return teamMapper.toDto(teamRepository.save(team));
    }

    @Override
    public TeamResponseDto getTeamInfo(Long team_id) {
        Team team = teamRepository.findById(team_id)
                .orElseThrow(() ->
                        new RuntimeException("Team not found!"));

        return teamMapper.toDto(team);
    }

    @Override
    public List<TeamResponseDto> getAllTeams() {
        return teamMapper.toDtoList(teamRepository.findAll());
    }
}
