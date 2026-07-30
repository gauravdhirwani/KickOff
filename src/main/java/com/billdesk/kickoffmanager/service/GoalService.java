package com.billdesk.kickoffmanager.service;

import com.billdesk.kickoffmanager.dto.CreateGoalRequest;
import com.billdesk.kickoffmanager.dto.GoalResponseDto;

import java.util.List;

public interface GoalService {

    //getall, getByFixture, createGoal

    public List<GoalResponseDto> getAllGoalsOfTournament();

    public List<GoalResponseDto> getByFixture(Long fixture_id);

    public GoalResponseDto createGoal(CreateGoalRequest createGoalRequest);

}
