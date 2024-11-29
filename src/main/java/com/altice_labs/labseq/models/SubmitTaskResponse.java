package com.altice_labs.labseq.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
@Schema(description = "Response model for submit task request")
public class SubmitTaskResponse {

    @Schema(description = "computation task id")
    private UUID taskId;
}
