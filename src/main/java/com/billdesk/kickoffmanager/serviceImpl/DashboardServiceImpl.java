package com.billdesk.kickoffmanager.serviceImpl;

import com.billdesk.kickoffmanager.dto.DashboardStatsDto;
import com.billdesk.kickoffmanager.dto.PlayerStatDto;
import com.billdesk.kickoffmanager.enums.Role;
import com.billdesk.kickoffmanager.repository.GoalRepository;
import com.billdesk.kickoffmanager.repository.TeamRepository;
import com.billdesk.kickoffmanager.repository.UserRepository;
import com.billdesk.kickoffmanager.service.DashboardService;
import com.billdesk.kickoffmanager.service.StatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private static final Long TOURNAMENT_ID = 1L; // hardcoded per your single-tournament setup

    private final GoalRepository goalRepository;
    private final TeamRepository teamRepository;
    private final UserRepository userRepository;
    private final StatsService statsService;

    @Override
    public DashboardStatsDto getDashboardStats() {

        long totalGoals = goalRepository.countByFixture_Tournament_Id(TOURNAMENT_ID);
        long totalAssists = goalRepository.countByFixture_Tournament_IdAndAssisterIsNotNull(TOURNAMENT_ID);
        long totalUsers = userRepository.count();
        long totalAdmins = userRepository.countByRole(Role.ADMIN);
        long totalTeams = teamRepository.countByTournamentId(TOURNAMENT_ID);

        // NEW — pull top scorer/assister lists and take the first entry
        List<PlayerStatDto> topScorers = statsService.getTopScorers();
        List<PlayerStatDto> topAssisters = statsService.getTopAssisters();

        PlayerStatDto highestScorer = topScorers.isEmpty() ? null : topScorers.get(0);
        PlayerStatDto highestAssister = topAssisters.isEmpty() ? null : topAssisters.get(0);

        return new DashboardStatsDto(totalGoals, totalAssists, totalUsers, totalAdmins, totalTeams,highestScorer,
                highestAssister);
    }
}