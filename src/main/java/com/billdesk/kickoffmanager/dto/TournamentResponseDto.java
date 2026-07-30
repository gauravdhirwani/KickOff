package com.billdesk.kickoffmanager.dto;

import com.billdesk.kickoffmanager.enums.TournamentStatus;
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
public class TournamentResponseDto {
    private Long id;
    private String name;
    private TournamentStatus status;
    private Date startDate;
    private Date endDate;
}
