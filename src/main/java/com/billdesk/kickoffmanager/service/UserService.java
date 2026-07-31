package com.billdesk.kickoffmanager.service;

import com.billdesk.kickoffmanager.dto.*;

import java.util.List;

public interface UserService {

    public LoginResponseDto register(UserRequestDto requestDto);

    public LoginResponseDto login(String email, String password);

    UserResponseDto getUserById(Long id);
    List<UserResponseDto> getAllUsers();

    PlayerStatsDto getPlayerStats(Long playerId);
    List<UserResponseDto> getUnassignedPlayers();
    UserResponseDto assignPlayerToTeam(Long playerId, Long teamId);
}
