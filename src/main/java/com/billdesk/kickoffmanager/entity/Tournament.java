package com.billdesk.kickoffmanager.entity;

import com.billdesk.kickoffmanager.enums.TournamentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tournaments")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private TournamentStatus status;

    private Date startDate;
    private Date endDate;

    @OneToMany(mappedBy = "tournament")
    private List<Fixture> fixtures;

    @OneToMany(mappedBy = "tournament")
    private List<Team> teams;

}