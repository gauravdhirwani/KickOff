package com.billdesk.kickoffmanager.repository;

import com.billdesk.kickoffmanager.entity.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TournamentRepository extends JpaRepository<Tournament,Long> {
}
