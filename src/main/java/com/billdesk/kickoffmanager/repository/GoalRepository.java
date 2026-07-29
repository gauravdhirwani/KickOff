package com.billdesk.kickoffmanager.repository;

import com.billdesk.kickoffmanager.entity.Goal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoalRepository extends JpaRepository<Goal,Long> {
}
