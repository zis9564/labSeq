package com.altice_labs.labseq.config;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

import static java.lang.Boolean.FALSE;
import static java.math.BigInteger.ONE;
import static java.math.BigInteger.ZERO;

@Slf4j
@Component
@RequiredArgsConstructor
public class RedisInitializer {

    private final RedisTemplate<Integer, BigInteger> redisTemplate;

    @PostConstruct
    public void init() {

        var isInitialized = redisTemplate.hasKey(0);

        if (FALSE.equals(isInitialized)) {
            redisTemplate.opsForValue().set(0, ZERO);
            redisTemplate.opsForValue().set(1, ONE);
            redisTemplate.opsForValue().set(2, ZERO);
            redisTemplate.opsForValue().set(3, ONE);
            log.info("Default values initialized in Redis.");
        } else {
            log.info("Redis already initialized. Skipping values setup.");
        }
    }
}
