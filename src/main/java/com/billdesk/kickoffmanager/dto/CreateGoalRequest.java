package com.billdesk.kickoffmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CreateGoalRequest {
    private Long fixtureId;
    private Long scorerId;
    private Long assistId;
    private Long scorerTeamId;
    private int minute;
}
