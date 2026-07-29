package com.billdesk.kickoffmanager.entity;


import com.billdesk.kickoffmanager.enums.FixtureStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "fixtures")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Fixture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //foreign keys!
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "home_team_id")
    private Team homeTeam;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "away_team_id")
    private Team awayTeam;

    private Date matchDate;

    private int homeScore;

    private int awayScore;

    @Enumerated(EnumType.STRING)
    private FixtureStatus status;

    @OneToMany(mappedBy = "fixture")
    private List<Goal> goals;
}
