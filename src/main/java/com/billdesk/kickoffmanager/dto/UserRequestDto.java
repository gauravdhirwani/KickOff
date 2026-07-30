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
public class UserRequestDto {

    private String name;
    private String email;
    private String password;
    private Role role;
    private Position position;
    private int jerseyNumber;
    private Long teamId;
}