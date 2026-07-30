package com.billdesk.kickoffmanager.controller;

import com.billdesk.kickoffmanager.dto.PlayerStatDto;
import com.billdesk.kickoffmanager.dto.TeamStandingDto;
import com.billdesk.kickoffmanager.service.StatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StatsController {

    private final StatsService statsService;

    @GetMapping("/stats/top-scorers")
    public ResponseEntity<List<PlayerStatDto>> getTopScorers() {
        return ResponseEntity.ok(statsService.getTopScorers());
    }

    @GetMapping("/stats/top-assists")
    public ResponseEntity<List<PlayerStatDto>> getTopAssisters() {
        return ResponseEntity.ok(statsService.getTopAssisters());
    }

    @GetMapping("/leaderboard")
    public ResponseEntity<List<TeamStandingDto>> getLeaderboard() {
        return ResponseEntity.ok(statsService.getLeaderboard());
    }
}