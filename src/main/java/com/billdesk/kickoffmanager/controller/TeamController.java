package com.billdesk.kickoffmanager.controller;


import com.billdesk.kickoffmanager.dto.TeamRequestDto;
import com.billdesk.kickoffmanager.dto.TeamResponseDto;
import com.billdesk.kickoffmanager.dto.TournamentResponseDto;
import com.billdesk.kickoffmanager.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/teams")
public class TeamController {

    private final TeamService service;

    @GetMapping("/{id}")
    public ResponseEntity<TeamResponseDto> getTeam(
            @PathVariable Long id
    ){
        return ResponseEntity.ok(service.getTeamInfo(id));
    }

    @GetMapping()
    public ResponseEntity<List<TeamResponseDto>> getAllTeams(){
        return ResponseEntity.ok(
                service.getAllTeams()
        );
    }

    @PostMapping()
    public ResponseEntity<TeamResponseDto> createTeam(
            @RequestBody TeamRequestDto requestDto
            ){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.createTeam(requestDto.getName(), requestDto.getTournamentId()));
    }

}
