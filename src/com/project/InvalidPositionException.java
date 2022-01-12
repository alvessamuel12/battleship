package com.project;

public class InvalidPositionException extends RuntimeException {
    InvalidPositionException() {
        super("Ops! Posicionamento inválido! Por favor, repita novamente.\n\n");
    }
}
