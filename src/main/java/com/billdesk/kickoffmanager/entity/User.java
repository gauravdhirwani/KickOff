package com.billdesk.kickoffmanager.entity;

import com.billdesk.kickoffmanager.enums.Position;
import com.billdesk.kickoffmanager.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "users")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    private int jerseyNumber;

    @Enumerated(EnumType.STRING)
    private Position position;

    //foreign key
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", nullable = true)
    private Team team;

    @OneToMany(mappedBy = "scorer")
    private List<Goal> goals;

    @OneToMany(mappedBy = "assister")
    private List<Goal> assists;

}
