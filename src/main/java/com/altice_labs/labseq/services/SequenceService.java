package com.altice_labs.labseq.services;

import com.altice_labs.labseq.cache.RedisRepository;
import com.altice_labs.labseq.exceptions.KeyNotFoundException;
import com.altice_labs.labseq.exceptions.UnsupportedValueException;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class SequenceService {

    private final RedisRepository repository;

    @Async
    public CompletableFuture<BigInteger> retrieveValueAsync(Integer key) {
        return CompletableFuture.supplyAsync(() -> retrieveValue(key));
    }

    public BigInteger retrieveValue(Integer key) {
        if (key < 0) {
            throw new UnsupportedValueException();
        }
        return repository
                .findByKey(key)
                .orElseGet(() -> calculate(biSearch(0, key), key));
    }

    private BigInteger calculate(int key, final int lastKey) {
        while (key < lastKey) {
            var a = repository.findByKey(key - 4)
                    .orElseThrow(KeyNotFoundException::new);
            var b = repository.findByKey(key - 3)
                    .orElseThrow(KeyNotFoundException::new);
            repository.save(key, a.add(b));
            ++key;
        }
        return repository.findByKey(lastKey-1)
                .orElseThrow(KeyNotFoundException::new);
    }

    private int biSearch(int left, int right) {
        if (left >= right) {
            return left;
        }

        int mid = left + (right - left) / 2;

        return repository.isExist(mid)
                ? biSearch(mid + 1, right)
                : biSearch(left, mid);
    }
}
