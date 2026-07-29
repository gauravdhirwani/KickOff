package com.billdesk.kickoffmanager.repository;

import com.billdesk.kickoffmanager.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team,Long> {

}
