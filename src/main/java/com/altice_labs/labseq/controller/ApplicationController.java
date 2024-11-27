package com.altice_labs.labseq.controller;

import com.altice_labs.labseq.service.SequenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/labseq")
public class ApplicationController {

    private final SequenceService service;

    @GetMapping("/{num}")
    public ResponseEntity<Integer> getNumber(@PathVariable(name = "num") Integer num) {
        var result = service.getByNum(num);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/save/{num}")
    public ResponseEntity<Void> saveNumber(@PathVariable(name = "num") Integer num) {
        service.save(num);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

}
