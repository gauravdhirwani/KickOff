package com.billdesk.kickoffmanager.repository;

import com.billdesk.kickoffmanager.entity.User;
import com.billdesk.kickoffmanager.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);

    long countByRole(Role role);

    List<User> findByTeamIsNullAndRole(Role role);

}
