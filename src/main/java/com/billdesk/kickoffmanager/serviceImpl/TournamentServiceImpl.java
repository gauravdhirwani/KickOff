package com.billdesk.kickoffmanager.serviceImpl;

import com.billdesk.kickoffmanager.dto.TournamentResponseDto;
import com.billdesk.kickoffmanager.entity.Tournament;
import com.billdesk.kickoffmanager.mapper.TournamentMapper;
import com.billdesk.kickoffmanager.repository.TournamentRepository;
import com.billdesk.kickoffmanager.service.TournamentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static com.billdesk.kickoffmanager.enums.TournamentStatus.COMPLETED;
import static com.billdesk.kickoffmanager.enums.TournamentStatus.ONGOING;

@Service
@RequiredArgsConstructor
public class TournamentServiceImpl implements TournamentService {
    private final TournamentMapper tournamentMapper;
    private final TournamentRepository tournamentRepository;

    @Override
    public TournamentResponseDto createTournament(String name) {
        Tournament tournament = new Tournament();
        tournament.setName(name);
        tournament.setStartDate(new Date());
        tournament.setStatus(ONGOING);
        return tournamentMapper.toDto(
                tournamentRepository.save(tournament)
        );
    }

    @Override
    public TournamentResponseDto endTournament(Long id) {

        Tournament tournament = tournamentRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Tournament Not found!")
                );
        tournament.setStatus(COMPLETED);
        tournament.setEndDate(new Date());

        return tournamentMapper.toDto(
                tournamentRepository.save(tournament)
        );

    }

    @Override
    public TournamentResponseDto displayTournament(Long id) {
        Tournament tournament = tournamentRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Tournament Not found!"));

        return tournamentMapper.toDto(tournament);
    }

    @Override
    public List<TournamentResponseDto> displayAllTournaments() {
        return tournamentMapper.toDtoList(
                tournamentRepository.findAll()
        );
    }
}
