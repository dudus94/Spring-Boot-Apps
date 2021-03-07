/*
 * Copyright 2021 fOrest94
 * All Rights Reserved
 */
package it.dudus94.redis.service;

import it.dudus94.redis.model.BoxingFight;
import it.dudus94.redis.repository.BoxingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.mockito.Mockito.*;

public class BoxingServiceTest {

    private BoxingRepository repository;
    private BoxingFight fight;
    private final String FIGHT_ID = "KlitschkoVsLewis";
    private BoxingService boxingService;

    @BeforeEach
    public void setup() {
        repository = mock(BoxingRepository.class);
        fight = mock(BoxingFight.class);
        boxingService = new BoxingService(repository);
    }

    @Test
    public void updateFightStatusWhenStartedOrFinished(){
        when(repository.findById(FIGHT_ID)).thenReturn(getInitFightStatus());
        boxingService.fightObserver();

        when(repository.findById(FIGHT_ID)).thenReturn(getOneBeforeLastFightStatus());
        boxingService.fightObserver();

        verify(repository, times(2)).save(isA(BoxingFight.class));
    }

    @Test
    public void updateFightStatusWhenNullOrNA() {
        boxingService.fightObserver();

        when(repository.findById(FIGHT_ID)).thenReturn(getNaFightStatus());
        boxingService.fightObserver();

        verify(repository, never()).save(fight);
    }

    private Optional<BoxingFight> getInitFightStatus() {
        return Optional.of(new BoxingFight(FIGHT_ID, BoxingFight.Status.STARTED, 1));
    }

    private Optional<BoxingFight> getOneBeforeLastFightStatus() {
        return Optional.of(new BoxingFight(FIGHT_ID, BoxingFight.Status.STARTED, 11));
    }

    private Optional<BoxingFight> getNaFightStatus() {
        return Optional.of(new BoxingFight(FIGHT_ID, BoxingFight.Status.NA, 0));
    }
}