package com.billdesk.kickoffmanager.repository;

import com.billdesk.kickoffmanager.entity.Fixture;
import com.billdesk.kickoffmanager.enums.FixtureStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FixtureRepository extends JpaRepository<Fixture,Long> {
    List<Fixture> findByTournamentIdAndStatus(Long tournamentId, FixtureStatus status);
}
