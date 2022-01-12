package com.project;

public class PositionConverter {

    private static final String VALIDATOR_EXPRESSION = "(?i)[A-J]{1}\\d{1}";
    private static char[] positions  = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};

    public static int convertPositionToNumber (char toBeConvertedPosition) {
        int numberPosition = 999999999;
        for (int i = 0; i < positions.length; i++) {
            if (Character.toUpperCase(toBeConvertedPosition) == Character.toUpperCase(positions[i]) || 
                (Character.getNumericValue(toBeConvertedPosition)) == i) {
                numberPosition = i;
            }
        }
        return numberPosition;
    }

    public static char convertNumberToLetter(int number) {
        return positions[number];
    } 

    public static Position convertToPosition(String inputPosition) {
        int row = convertPositionToNumber(inputPosition.charAt(0));
        int column = Integer.parseInt(Character.toString(inputPosition.charAt(1)));
        return new Position(row, column);
    }

    public static boolean validatePosition(String inputPosition) {
        return inputPosition.matches(VALIDATOR_EXPRESSION);
    }
}

