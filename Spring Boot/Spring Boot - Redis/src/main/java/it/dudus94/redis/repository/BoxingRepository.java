/*
 * Copyright 2021 fOrest94
 * All Rights Reserved
 */
package it.dudus94.redis.repository;

import it.dudus94.redis.model.BoxingFight;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoxingRepository extends CrudRepository<BoxingFight, String> {}