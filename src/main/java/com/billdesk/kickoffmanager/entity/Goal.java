package com.billdesk.kickoffmanager.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //foreign keys
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fixture_id")
    private Fixture fixture;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "scorer_id")
    private User scorer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assister_id")
    private User assister;

    @Min(value = 1)
    @Max(value = 90)
    private int minute;
}
