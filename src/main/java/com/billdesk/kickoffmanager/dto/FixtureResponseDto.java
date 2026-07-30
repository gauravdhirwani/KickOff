package com.billdesk.kickoffmanager.dto;

import com.billdesk.kickoffmanager.entity.Team;
import com.billdesk.kickoffmanager.entity.Tournament;
import com.billdesk.kickoffmanager.enums.FixtureStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FixtureResponseDto {
    private long id;
    private String  tournamentName;
    private String homeTeam;
    private String awayTeam;
    private Date matchDate;
    private int homeScore;
    private int awayScore;
    private FixtureStatus status;
}
