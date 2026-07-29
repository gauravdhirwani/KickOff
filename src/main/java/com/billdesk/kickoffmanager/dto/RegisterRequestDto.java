package com.billdesk.kickoffmanager.dto;

import com.billdesk.kickoffmanager.enums.Position;
import com.billdesk.kickoffmanager.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequestDto {
    private String name;

    private String email;

    private String password;

    private Role role;

    private int jerseyNumber;

    private Position position;
}
