package com.project;

public class PosititionConverter {
    private char[] positions  = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};

    public int convertPositionToNumber (char chosenPosition) {
        int numberPosition = 999999999;
        for (int i = 0; i < this.positions.length; i++) {
            if (chosenPosition == positions[i]) {
                numberPosition = i;
            };
        }
        return numberPosition;
    }
}
