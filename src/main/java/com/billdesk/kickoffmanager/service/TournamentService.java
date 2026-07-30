package com.billdesk.kickoffmanager.service;

import com.billdesk.kickoffmanager.dto.TournamentResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TournamentService {
    public TournamentResponseDto createTournament(String name);

    public TournamentResponseDto endTournament(Long id);

    public TournamentResponseDto displayTournament(Long id);

    public List<TournamentResponseDto> displayAllTournaments();
}
