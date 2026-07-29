package com.billdesk.kickoffmanager.dto;

import com.billdesk.kickoffmanager.enums.Position;
import com.billdesk.kickoffmanager.enums.Role;

public class UserRequestDto {

    private String name;
    private String email;
    private String password;
    private Role role;
    private Position position;
    private int jerseyNumber;
    private int teamId;
}