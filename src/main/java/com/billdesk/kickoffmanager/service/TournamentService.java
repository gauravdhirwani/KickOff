package com.billdesk.kickoffmanager.service;

import com.billdesk.kickoffmanager.dto.TournamentResponseDto;
import org.springframework.http.ResponseEntity;

public interface TournamentService {
    public ResponseEntity<String> createTournament(String name);

    public ResponseEntity<String> endTournament();

    public TournamentResponseDto displayTournament();
}
