package com.project;

public class InvalidPositionException extends RuntimeException {
    InvalidPositionException() {
        super("Posicao invalida!");
    }
}
