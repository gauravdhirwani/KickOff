package com.billdesk.kickoffmanager.serviceImpl;

import com.billdesk.kickoffmanager.dto.CreateGoalRequest;
import com.billdesk.kickoffmanager.dto.GoalResponseDto;
import com.billdesk.kickoffmanager.entity.Fixture;
import com.billdesk.kickoffmanager.entity.Goal;
import com.billdesk.kickoffmanager.entity.Team;
import com.billdesk.kickoffmanager.entity.User;
import com.billdesk.kickoffmanager.enums.FixtureStatus;
import com.billdesk.kickoffmanager.mapper.GoalMapper;
import com.billdesk.kickoffmanager.repository.FixtureRepository;
import com.billdesk.kickoffmanager.repository.GoalRepository;
import com.billdesk.kickoffmanager.repository.TeamRepository;
import com.billdesk.kickoffmanager.repository.UserRepository;
import com.billdesk.kickoffmanager.service.GoalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GoalServiceImpl implements GoalService {

    private final GoalRepository goalRepository;
    private final GoalMapper goalMapper;
    private final FixtureRepository fixtureRepository;
    private final UserRepository userRepository;
    private final TeamRepository teamRepository;

    @Override
    public List<GoalResponseDto> getAllGoalsOfTournament() {
        return goalMapper.toListDto(
                goalRepository.findAll()
        );
    }

    @Override
    public List<GoalResponseDto> getByFixture(Long fixture_id) {
        return goalMapper.toListDto(
                goalRepository.findByFixtureId(fixture_id)
        );
    }

    @Override
    public GoalResponseDto createGoal(CreateGoalRequest createGoalRequest) {

        Fixture fixture = fixtureRepository.findById(createGoalRequest.getFixtureId())
                .orElseThrow(() ->
                        new RuntimeException("Fixture not found!"));

        User scorer = userRepository.findById(createGoalRequest.getScorerId())
                .orElseThrow(() ->
                        new RuntimeException("Scorer not found!"));

        User assister = userRepository.findById(createGoalRequest.getAssistId())
                .orElseThrow(() ->
                        new RuntimeException("Assister not found!"));

        Team scorerTeam = teamRepository.findById(createGoalRequest.getScorerTeamId())
                .orElseThrow(() ->
                        new RuntimeException("Scorer Team not found!"));

        if(fixture.getStatus().equals(FixtureStatus.COMPLETED)){
            new RuntimeException("Fixture is completed;cannot add goal!");
        }

        Goal goal = new Goal();
        goal.setFixture(fixture);
        goal.setScorer(scorer);
        goal.setAssister(assister);
        goal.setMinute(createGoalRequest.getMinute());

        //increment score of scorer team id in fixture table
        if(fixture.getHomeTeam().getId() == scorerTeam.getId()){
            fixture.setHomeScore(fixture.getHomeScore()+1);
        }else{
            fixture.setAwayScore(fixture.getAwayScore()+1);
        }

        return goalMapper.toDto(
                goalRepository.save(goal)
        );

    }
}
