package com.altice_labs.labseq.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Response model for computation result")
public class SequenceComputationResponse {

    @Schema(description = "computation result value", requiredMode = REQUIRED)
    private BigInteger value;

}
