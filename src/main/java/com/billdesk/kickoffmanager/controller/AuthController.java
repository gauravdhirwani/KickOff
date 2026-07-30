package com.billdesk.kickoffmanager.controller;

import com.billdesk.kickoffmanager.dto.*;
import com.billdesk.kickoffmanager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<LoginResponseDto> register(
            @RequestBody RegisterRequestDto requestDto
    ){

        return ResponseEntity.ok(
            userService.register(requestDto)
        );
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(
            @RequestBody LoginRequestDto requestDto
            ){

        String email = requestDto.getEmail();
        String password = requestDto.getPassword();
        return ResponseEntity.ok(
                userService.login(email,password)
        );
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponseDto> getMe(@RequestAttribute Long userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserById(
            @PathVariable Long id,
            @RequestAttribute String userRole
    ){
        if (!"ADMIN".equals(userRole)) {
            throw new RuntimeException("Only admins can do this");
        }
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers(
            @RequestAttribute String userRole
    ){
        if (!"ADMIN".equals(userRole)) {
            throw new RuntimeException("Only admins can do this");
        }
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{playerId}/stats")
    public ResponseEntity<PlayerStatsDto> getPlayerStats(@PathVariable Long playerId) {
        return ResponseEntity.ok(userService.getPlayerStats(playerId));
    }

    @GetMapping("/players/unassigned")
    public ResponseEntity<List<UserResponseDto>> getUnassignedPlayers(@RequestAttribute String userRole) {
        if (!"ADMIN".equals(userRole)) {
            throw new RuntimeException("Only admins can do this");
        }
        return ResponseEntity.ok(userService.getUnassignedPlayers());
    }

    @PatchMapping("/{id}/assign-team")
    public ResponseEntity<UserResponseDto> assignPlayerToTeam(
            @PathVariable Long id,
            @RequestParam Long teamId,
            @RequestAttribute String userRole) {

        if (!"ADMIN".equals(userRole)) {
            throw new RuntimeException("Only admins can do this");
        }
        return ResponseEntity.ok(userService.assignPlayerToTeam(id, teamId));
    }
}
