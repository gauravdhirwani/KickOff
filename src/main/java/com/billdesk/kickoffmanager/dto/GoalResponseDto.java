package com.billdesk.kickoffmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GoalResponseDto {
    private Long fixtureId;
    private String scorer;
    private String assistBy;
    private int minute;
}
