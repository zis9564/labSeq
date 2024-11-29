package com.altice_labs.labseq.exceptions;

public class UnsupportedValueException extends RuntimeException {

    public UnsupportedValueException() {
        super("Numbers in sequence cannot be negative");
    }
}
