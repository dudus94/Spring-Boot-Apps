/*
 * Copyright 2021 fOrest94
 * All Rights Reserved
 */
package it.dudus94.redis.rest;

import it.dudus94.redis.model.BoxingFight;
import it.dudus94.redis.service.BoxingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class BoxingController {

    private final BoxingService boxingService;

    public BoxingController(BoxingService boxingService) {
        this.boxingService = boxingService;
    }

    @GetMapping("/start")
    public void startFight() {
        boxingService.initFight();
    }

    @GetMapping("/status")
    public ResponseEntity<BoxingFight> getFightStatus() {

        Optional<BoxingFight> status = boxingService.getFightStatus();
        return status.map(boxingFight -> new ResponseEntity<>(boxingFight, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @GetMapping()
    public ResponseEntity<String> healthcheck() {
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}
