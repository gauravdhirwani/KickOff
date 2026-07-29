package com.billdesk.kickoffmanager.controller;

import com.billdesk.kickoffmanager.dto.*;
import com.billdesk.kickoffmanager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
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
}
