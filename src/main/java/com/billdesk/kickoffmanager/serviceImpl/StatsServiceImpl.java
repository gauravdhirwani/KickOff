package com.billdesk.kickoffmanager.serviceImpl;

import com.billdesk.kickoffmanager.dto.PlayerStatDto;
import com.billdesk.kickoffmanager.dto.TeamStandingDto;
import com.billdesk.kickoffmanager.entity.Fixture;
import com.billdesk.kickoffmanager.entity.Team;
import com.billdesk.kickoffmanager.enums.FixtureStatus;
import com.billdesk.kickoffmanager.repository.FixtureRepository;
import com.billdesk.kickoffmanager.repository.GoalRepository;
import com.billdesk.kickoffmanager.repository.TeamRepository;
import com.billdesk.kickoffmanager.service.StatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StatsServiceImpl implements StatsService {

    private static final Long TOURNAMENT_ID = 1L;

    private final GoalRepository goalRepository;
    private final FixtureRepository fixtureRepository;
    private final TeamRepository teamRepository;

    @Override
    public List<PlayerStatDto> getTopScorers() {
        return goalRepository.findTopScorersRaw(TOURNAMENT_ID).stream()
                .map(row -> new PlayerStatDto((Long) row[0], (String) row[1], (Long) row[2]))
                .collect(Collectors.toList());
    }

    @Override
    public List<PlayerStatDto> getTopAssisters() {
        return goalRepository.findTopAssistersRaw(TOURNAMENT_ID).stream()
                .map(row -> new PlayerStatDto((Long) row[0], (String) row[1], (Long) row[2]))
                .collect(Collectors.toList());
    }

    @Override
    public List<TeamStandingDto> getLeaderboard() {

        List<Team> teams = teamRepository.findByTournamentId(TOURNAMENT_ID);
        List<Fixture> completedFixtures =
                fixtureRepository.findByTournamentIdAndStatus(TOURNAMENT_ID, FixtureStatus.COMPLETED);

        Map<Long, TeamStandingDto> standingsMap = new HashMap<>();
        for (Team team : teams) {
            standingsMap.put(team.getId(),
                    new TeamStandingDto(team.getId(), team.getName(), 0, 0, 0, 0, 0, 0, 0, 0));
        }

        for (Fixture fixture : completedFixtures) {
            Long homeId = fixture.getHomeTeam().getId();
            Long awayId = fixture.getAwayTeam().getId();
            int homeScore = fixture.getHomeScore();
            int awayScore = fixture.getAwayScore();

            TeamStandingDto home = standingsMap.get(homeId);
            TeamStandingDto away = standingsMap.get(awayId);
            if (home == null || away == null) continue; // safety check

            home.setPlayed(home.getPlayed() + 1);
            away.setPlayed(away.getPlayed() + 1);
            home.setGoalsFor(home.getGoalsFor() + homeScore);
            home.setGoalsAgainst(home.getGoalsAgainst() + awayScore);
            away.setGoalsFor(away.getGoalsFor() + awayScore);
            away.setGoalsAgainst(away.getGoalsAgainst() + homeScore);

            if (homeScore > awayScore) {
                home.setWins(home.getWins() + 1);
                home.setPoints(home.getPoints() + 3);
                away.setLosses(away.getLosses() + 1);
            } else if (homeScore < awayScore) {
                away.setWins(away.getWins() + 1);
                away.setPoints(away.getPoints() + 3);
                home.setLosses(home.getLosses() + 1);
            } else {
                home.setDraws(home.getDraws() + 1);
                away.setDraws(away.getDraws() + 1);
                home.setPoints(home.getPoints() + 1);
                away.setPoints(away.getPoints() + 1);
            }
        }

        for (TeamStandingDto standing : standingsMap.values()) {
            standing.setGoalDifference(standing.getGoalsFor() - standing.getGoalsAgainst());
        }

        return standingsMap.values().stream()
                .sorted(Comparator
                        .comparingInt(TeamStandingDto::getPoints).reversed()
                        .thenComparing(Comparator.comparingInt(TeamStandingDto::getGoalDifference).reversed()))
                .collect(Collectors.toList());
    }
}