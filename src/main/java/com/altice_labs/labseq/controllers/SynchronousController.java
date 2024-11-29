package com.altice_labs.labseq.controllers;

import com.altice_labs.labseq.services.SequenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/labseq")
public class SynchronousController {

    private final SequenceService service;

    @GetMapping("/{num}")
    public ResponseEntity<BigInteger> getNumber(@PathVariable(name = "num") Long num) {
        return new ResponseEntity<>(service.retrieveValue(num.intValue()), OK);
    }
}
