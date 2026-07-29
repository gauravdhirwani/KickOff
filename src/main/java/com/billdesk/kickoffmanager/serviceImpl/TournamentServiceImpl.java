package com.billdesk.kickoffmanager.serviceImpl;

import com.billdesk.kickoffmanager.dto.TournamentResponseDto;
import com.billdesk.kickoffmanager.entity.Tournament;
import com.billdesk.kickoffmanager.service.TournamentService;
import org.springframework.http.ResponseEntity;

import java.util.Date;

import static com.billdesk.kickoffmanager.enums.TournamentStatus.ONGOING;

public class TournamentServiceImpl implements TournamentService {
    @Override
    public ResponseEntity<String> createTournament(String name) {
        Tournament tournament = new Tournament();
        tournament.setName(name);
        tournament.setStartDate(new Date());
        tournament.setStatus(ONGOING);

        return null;
    }

    @Override
    public ResponseEntity<String> endTournament() {
        return null;
    }

    @Override
    public TournamentResponseDto displayTournament() {
        return null;
    }
}
