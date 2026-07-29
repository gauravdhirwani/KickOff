package com.billdesk.kickoffmanager.dto;


import com.billdesk.kickoffmanager.enums.Position;
import com.billdesk.kickoffmanager.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {
    private long id;
    private String name;
    private String email;
    private Role role;
    private int jersey_number;
    private Position position;
    private long team_id;
}
