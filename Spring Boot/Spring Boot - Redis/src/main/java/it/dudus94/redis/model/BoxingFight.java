/*
 * Copyright 2021 fOrest94
 * All Rights Reserved
 */
package it.dudus94.redis.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@RedisHash("fightStatus")
public class BoxingFight implements Serializable {

    private String id;
    private Status status;
    private int round;

    public enum Status {
        NA, STARTED, FINISHED
    }
}