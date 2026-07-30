package com.billdesk.kickoffmanager.controller;

import com.billdesk.kickoffmanager.dto.CreateFixtureRequestDto;
import com.billdesk.kickoffmanager.dto.FixtureResponseDto;
import com.billdesk.kickoffmanager.service.FixtureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/fixtures")
public class FixtureController {
    private final FixtureService service;

    @PostMapping
    public ResponseEntity<FixtureResponseDto> createFixture(
            @RequestBody CreateFixtureRequestDto createFixtureRequestDto
            ){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                service.createFixture(createFixtureRequestDto)
        );
    }

    @GetMapping
    public ResponseEntity<List<FixtureResponseDto>> getAllFixtures(){
        return ResponseEntity.ok(service.getAllFixtures());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FixtureResponseDto> getFixtureById(
            @PathVariable Long id){
        return ResponseEntity.ok(
                service.getFixtureById(id)
        );
    }
}
