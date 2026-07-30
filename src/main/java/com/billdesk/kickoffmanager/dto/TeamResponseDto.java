package com.billdesk.kickoffmanager.dto;


import com.billdesk.kickoffmanager.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TeamResponseDto {
    private Long id;
    private String name;
    private Long tournamentId;
    private List<User> players;
}
