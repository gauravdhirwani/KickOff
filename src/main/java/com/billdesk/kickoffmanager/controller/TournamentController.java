package com.billdesk.kickoffmanager.controller;

import com.billdesk.kickoffmanager.dto.TournamentRequestDto;
import com.billdesk.kickoffmanager.dto.TournamentResponseDto;
import com.billdesk.kickoffmanager.service.TournamentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tournaments")
@RequiredArgsConstructor
public class TournamentController {
    private final TournamentService tournamentService;

    @PostMapping
    public ResponseEntity<TournamentResponseDto> create(
            @RequestBody TournamentRequestDto tournamentRequestDto
            ){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(tournamentService.createTournament(tournamentRequestDto.getName()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TournamentResponseDto> getById(
            @PathVariable Long id
    ){
        return ResponseEntity.ok(tournamentService.displayTournament(id));
    }

    @PatchMapping("/{id}/end")
    public ResponseEntity<TournamentResponseDto> endTourney(
            @PathVariable Long id
    ){
        return ResponseEntity.ok(tournamentService.endTournament(id));
    }

    @GetMapping
    public ResponseEntity<List<TournamentResponseDto>> getAll(){
        return ResponseEntity.ok(
                tournamentService.displayAllTournaments()
        );
    }


}
