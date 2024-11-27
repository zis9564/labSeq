package com.altice_labs.labseq.cache;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class RedisRepository {

    private final RedisTemplate<Integer, Integer> redisTemplate;

    public void save(int num) {
        redisTemplate.opsForValue().set(1, num);
    }

    public int findByNum(int num) {
        return redisTemplate.opsForValue().get(num);
    }
}
