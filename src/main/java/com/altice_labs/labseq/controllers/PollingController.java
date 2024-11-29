package com.altice_labs.labseq.controllers;

import com.altice_labs.labseq.models.SequenceComputationResponse;
import com.altice_labs.labseq.models.SubmitTaskResponse;
import com.altice_labs.labseq.services.SequenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/labseq")
public class PollingController {

    private final SequenceService service;
    private final Map<UUID, CompletableFuture<BigInteger>> taskStatus = new ConcurrentHashMap<>();

    @PostMapping("/submit/{num}")
    public ResponseEntity<SubmitTaskResponse> submitTask(@PathVariable(name = "num") Long num) {
        var taskId = UUID.randomUUID();
        var task = service.retrieveValueAsync(num.intValue());
        taskStatus.put(taskId, task);
        return ResponseEntity.ok(new SubmitTaskResponse(taskId));
    }

    @GetMapping("/status/{taskId}")
    public ResponseEntity<?> pollResult(@PathVariable(name = "taskId") UUID taskId) {
        CompletableFuture<BigInteger> task = taskStatus.get(taskId);

        if (task == null) {
            return ResponseEntity.notFound().build();
        }

        if (task.isDone()) {
            try {
                var result = task.get();
                taskStatus.remove(taskId);
                return ResponseEntity.ok(new SequenceComputationResponse(result));
            } catch (InterruptedException | ExecutionException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred");
            }
        } else {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Task is still processing");
        }
    }
}
