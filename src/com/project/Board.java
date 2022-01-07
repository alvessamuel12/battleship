package com.project;

public class Board {
    private char[][] slots = new char[10][10];
    private PositionConverter converter = new PositionConverter();

    private void setShip (int row, int column) {
        this.slots[row][column] = 'N';
    }

    private boolean validatePositioning (int row, int column) {
        if (row < 0 || row > 9 || column < 0 || column > 9) {
            return false;
        } if (this.slots[row][column] != 0) {
            return false;
        } else {
            return true;
        }
    }

    public boolean getPosition (String chosenPosition) {
        char toBeConvertedRow = chosenPosition.charAt(0);
        int row = this.converter.convertPositionToNumber(toBeConvertedRow);
        int column = Character.getNumericValue(chosenPosition.charAt(1));
        boolean validPosition = this.validatePositioning(row, column);

        if (validPosition) {
            this.setShip(row, column);
            return true;
        } else {
            return false;
        }
    }

    public void renderBoard () {
        for (int i = 0; i < this.slots.length; i++) {
            for (int j = 0; j < this.slots[i].length; j++) {
                System.out.print((this.slots[i][j] == 0 ? "   " : (" " + this.slots[i][j] + " ")) + (j != 9 ? "|" : ""));
                if (j == 9) {
                    System.out.printf("%n");
                }
            }
            if (i != 9) {
                System.out.println("---------------------------------------");
            }
        }
        System.out.printf("%n");
    }
}
