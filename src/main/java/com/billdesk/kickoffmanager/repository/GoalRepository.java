package com.billdesk.kickoffmanager.repository;

import com.billdesk.kickoffmanager.entity.Goal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GoalRepository extends JpaRepository<Goal,Long> {
    List<Goal> findByFixtureId(Long fixture_id);

    long countByFixture_Tournament_Id(Long tournamentId);

    long countByFixture_Tournament_IdAndAssisterIsNotNull(Long tournamentId);

    @Query("""
        SELECT g.scorer.id, g.scorer.name, COUNT(g)
        FROM Goal g
        WHERE g.fixture.tournament.id = :tournamentId
        GROUP BY g.scorer.id, g.scorer.name
        ORDER BY COUNT(g) DESC
        """)
    List<Object[]> findTopScorersRaw(@Param("tournamentId") Long tournamentId);

    @Query("""
        SELECT g.assister.id, g.assister.name, COUNT(g)
        FROM Goal g
        WHERE g.fixture.tournament.id = :tournamentId AND g.assister IS NOT NULL
        GROUP BY g.assister.id, g.assister.name
        ORDER BY COUNT(g) DESC
        """)
    List<Object[]> findTopAssistersRaw(@Param("tournamentId") Long tournamentId);

    long countByScorerId(Long playerId);
    long countByAssisterId(Long playerId);
}
