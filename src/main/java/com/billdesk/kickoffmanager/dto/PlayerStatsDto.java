package com.billdesk.kickoffmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class PlayerStatsDto {
    private Long playerId;
    private String name;
    private String teamName; // null if unassigned
    private long goals;
    private long assists;
}