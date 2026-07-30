package com.billdesk.kickoffmanager.controller;

import com.billdesk.kickoffmanager.dto.CreateGoalRequest;
import com.billdesk.kickoffmanager.dto.GoalResponseDto;
import com.billdesk.kickoffmanager.service.GoalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/goals")
public class GoalController {

    private final GoalService goalService;
    //getall, getByFixture, createGoal

    @GetMapping
    public ResponseEntity<List<GoalResponseDto>> getAllGoals(){
        return ResponseEntity.ok(
                goalService.getAllGoalsOfTournament()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<GoalResponseDto>> getAllGoalsByFixture(
            @PathVariable Long id
    ){
        return ResponseEntity.ok(
                goalService.getByFixture(id)
        );
    }

    @PostMapping
    public ResponseEntity<GoalResponseDto> createGoal(
            @RequestBody CreateGoalRequest createGoalRequest,
            @RequestAttribute String userRole
            ){
        if (!"ADMIN".equals(userRole)) {
            throw new RuntimeException("Only admins can do this");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(
                goalService.createGoal(createGoalRequest)
        );
    }

}
