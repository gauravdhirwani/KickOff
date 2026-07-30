package com.billdesk.kickoffmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class DashboardStatsDto {
    private long totalGoals;
    private long totalAssists;
    private long totalUsers;
    private long totalAdmins;
    private long totalTeams;
}