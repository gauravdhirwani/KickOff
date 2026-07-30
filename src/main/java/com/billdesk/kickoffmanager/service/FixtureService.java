package com.billdesk.kickoffmanager.service;

import com.billdesk.kickoffmanager.dto.CreateFixtureRequestDto;
import com.billdesk.kickoffmanager.dto.FixtureResponseDto;

import java.util.List;

public interface FixtureService {

    //create display displayall

    public FixtureResponseDto createFixture(CreateFixtureRequestDto dto);

    public FixtureResponseDto getFixtureById(Long id);

    public List<FixtureResponseDto> getAllFixtures();

}
