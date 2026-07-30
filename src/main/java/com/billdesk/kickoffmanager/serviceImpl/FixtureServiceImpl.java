package com.billdesk.kickoffmanager.serviceImpl;

import com.billdesk.kickoffmanager.dto.CreateFixtureRequestDto;
import com.billdesk.kickoffmanager.dto.FixtureResponseDto;
import com.billdesk.kickoffmanager.entity.Fixture;
import com.billdesk.kickoffmanager.entity.Team;
import com.billdesk.kickoffmanager.entity.Tournament;
import com.billdesk.kickoffmanager.mapper.FixtureMapper;
import com.billdesk.kickoffmanager.repository.FixtureRepository;
import com.billdesk.kickoffmanager.repository.TeamRepository;
import com.billdesk.kickoffmanager.repository.TournamentRepository;
import com.billdesk.kickoffmanager.service.FixtureService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static com.billdesk.kickoffmanager.enums.FixtureStatus.SCHEDULED;

@Service
@RequiredArgsConstructor
public class FixtureServiceImpl implements FixtureService {

    private final FixtureMapper fixtureMapper;
    private final FixtureRepository fixtureRepository;
    private final TournamentRepository tournamentRepository;
    private final TeamRepository teamRepository;

    @Override
    public FixtureResponseDto createFixture(CreateFixtureRequestDto dto) {

        Tournament tournament = tournamentRepository.findById(
                dto.getTournamentId()
        ).orElseThrow(()->
                new RuntimeException("Tournament not found!"));

        Team homeTeam = teamRepository.findById(
                dto.getHomeTeamId()
        ).orElseThrow(()->
                new RuntimeException("Home team not found!"));

        Team awayTeam = teamRepository.findById(
                dto.getAwayTeamId()
        ).orElseThrow(()->
                new RuntimeException("Away team not found!"));


        Fixture fixture = new Fixture();
        fixture.setHomeTeam(homeTeam);
        fixture.setAwayTeam(awayTeam);
        fixture.setTournament(tournament);
        fixture.setStatus(SCHEDULED);

        return fixtureMapper.toDto(fixtureRepository.save(fixture));

    }

    @Override
    public FixtureResponseDto getFixtureById(Long id) {
        Fixture fixture = fixtureRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Fixture not found!"));

        return fixtureMapper.toDto(fixture);
    }

    @Override
    public List<FixtureResponseDto> getAllFixtures() {
        return fixtureMapper.toListDto(
                fixtureRepository.findAll()
        );
    }
}
