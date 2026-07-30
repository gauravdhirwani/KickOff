package com.billdesk.kickoffmanager.dto;

import com.billdesk.kickoffmanager.enums.FixtureStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateFixtureRequestDto {
    private Long tournamentId;
    private Long homeTeamId;
    private Long awayTeamId;
}
