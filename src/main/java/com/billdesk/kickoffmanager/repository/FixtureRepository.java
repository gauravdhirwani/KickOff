package com.billdesk.kickoffmanager.repository;

import com.billdesk.kickoffmanager.entity.Fixture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FixtureRepository extends JpaRepository<Fixture,Long> {
}
