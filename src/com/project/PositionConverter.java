package com.project;

public class PositionConverter {
    private char[] positions  = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};

    public int convertPositionToNumber (char toBeConvertedPosition) {
        int numberPosition = 999999999;
        for (int i = 0; i < this.positions.length; i++) {
            if (Character.toUpperCase(toBeConvertedPosition) == Character.toUpperCase(this.positions[i]) || 
                (Character.getNumericValue(toBeConvertedPosition)) == i) {
                numberPosition = i;
            }
        }
        return numberPosition;
    }
}
