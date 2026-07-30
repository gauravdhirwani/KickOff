package com.billdesk.kickoffmanager.service;

import com.billdesk.kickoffmanager.dto.*;

import java.util.List;

public interface UserService {

    public LoginResponseDto register(RegisterRequestDto requestDto);

    public LoginResponseDto login(String email, String password);

    UserResponseDto getUserById(Long id);
    List<UserResponseDto> getAllUsers();
}
