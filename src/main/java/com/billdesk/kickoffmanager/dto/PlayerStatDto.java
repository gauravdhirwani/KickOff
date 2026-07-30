package com.billdesk.kickoffmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class PlayerStatDto {
    private Long playerId;
    private String name;
    private long count; // goals or assists, depending on which query filled it
}