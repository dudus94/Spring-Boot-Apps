/*
 * Copyright 2021 fOrest94
 * All Rights Reserved
 */
package it.dudus94.redis.service;

import it.dudus94.redis.model.BoxingFight;
import it.dudus94.redis.model.BoxingFight.Status;
import it.dudus94.redis.repository.BoxingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class BoxingService {

    private final BoxingRepository repository;
    private final String FIGHT_ID = "KlitschkoVsLewis";
    private final int ROUNDS_NUMBER = 12;

    public BoxingService(BoxingRepository repository) {
        this.repository = repository;
    }


    public void initFight() {
        repository.save(new BoxingFight(FIGHT_ID, Status.STARTED, 1));
    }

    @Scheduled(fixedRate = 1000)
    public void fightObserver() {
        BoxingFight fight = repository.findById(FIGHT_ID).orElse(getEmptyStatus());
        if (fight.getStatus() != Status.NA) {
            updateFightStatus(fight);
        }
    }

    private BoxingFight getEmptyStatus() {
        return new BoxingFight(FIGHT_ID, Status.NA, 0);
    }

    private void updateFightStatus(BoxingFight fight) {
        if (fight.getStatus() == Status.STARTED && fight.getRound() < ROUNDS_NUMBER - 1) {
            repository.save(new BoxingFight(FIGHT_ID, Status.STARTED, fight.getRound() + 1));
        } else if (fight.getStatus() == Status.STARTED && fight.getRound() == ROUNDS_NUMBER - 1) {
            repository.save(new BoxingFight(FIGHT_ID, Status.FINISHED, fight.getRound() + 1));
        } else
            repository.save(getEmptyStatus());
    }

    public Optional<BoxingFight> getFightStatus() {
        return repository.findById(FIGHT_ID);
    }
}
