package com.altice_labs.labseq.exceptions;

public class KeyNotFoundException extends RuntimeException {

    public KeyNotFoundException() {
        super("Key not found.");
    }
}
