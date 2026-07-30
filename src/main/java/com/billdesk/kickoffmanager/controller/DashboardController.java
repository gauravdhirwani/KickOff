package com.billdesk.kickoffmanager.controller;

import com.billdesk.kickoffmanager.dto.DashboardStatsDto;
import com.billdesk.kickoffmanager.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping
    public ResponseEntity<DashboardStatsDto> getDashboard(@RequestAttribute String userRole) {
        if (!"ADMIN".equals(userRole)) {
            throw new RuntimeException("Only admins can do this");
        }
        return ResponseEntity.ok(dashboardService.getDashboardStats());
    }
}