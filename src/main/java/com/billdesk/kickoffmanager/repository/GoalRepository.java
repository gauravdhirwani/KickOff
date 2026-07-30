package com.billdesk.kickoffmanager.repository;

import com.billdesk.kickoffmanager.entity.Goal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GoalRepository extends JpaRepository<Goal,Long> {
    List<Goal> findByFixtureId(Long fixture_id);
}
