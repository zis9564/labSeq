package com.altice_labs.labseq.service;

import com.altice_labs.labseq.cache.RedisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SequenceService {

    private final RedisRepository repository;

    public void save(int num) {
        repository.save(num);
    }

    public int getByNum(int num) {
        return repository.findByNum(num);
    }
}
