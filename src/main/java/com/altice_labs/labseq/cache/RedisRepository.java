package com.altice_labs.labseq.cache;

import com.altice_labs.labseq.exceptions.UnsupportedValueException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

import static java.math.BigInteger.ZERO;

@Slf4j
@Repository
@RequiredArgsConstructor
public class RedisRepository {

    private final RedisTemplate<Integer, BigInteger> redisTemplate;

    public BigInteger save(Integer key, BigInteger value) {
        if (key < 0 || value.compareTo(ZERO) < 0) {
            throw new UnsupportedValueException();
        }
        if (!redisTemplate.hasKey(key)) {
            redisTemplate.opsForValue().setIfAbsent(key, value);
        }
        return value;
    }

    public Optional<BigInteger> findByKey(int key) {
        var value = redisTemplate.opsForValue().get(key);
        return Optional.ofNullable(value);
    }

    public boolean isExist(int key) {
        return redisTemplate.hasKey(key);
    }
}
