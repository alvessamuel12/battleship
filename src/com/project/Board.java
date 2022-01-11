package com.project;

public class Board {
    private char[][] slots = new char[10][10];
    private PositionConverter converter = new PositionConverter();
    private final int SHIP_AMOUNT = 10;

    public int getSHIP_AMOUNT () {
        return this.SHIP_AMOUNT;
    }

    private void setShip (int row, int column) {
        this.slots[row][column] = 'N';
    }

    private boolean validateInput (String chosenPosition) {
        if (chosenPosition.length() != 2) {
            return false;
        } else {
            return true;
        }
    }

    private boolean validatePositioning (int row, int column) {
        if (row < 0 || row >= this.slots.length || column < 0 || column >= this.slots[0].length) {
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
        boolean validInput = this.validateInput(chosenPosition);
        boolean validPosition = this.validatePositioning(row, column);

        if (validInput && validPosition) {
            this.setShip(row, column);
            return true;
        } else {
            return false;
        }
    }

    public void renderBoard () {
        System.out.println("-----------------------------------------");
        for (int i = 0; i < this.slots.length; i++) {
            for (int j = 0; j < this.slots[i].length; j++) {
                System.out.print((j == 0 ? "|" : "") + (this.slots[i][j] == 0 ? "   " : (" " + this.slots[i][j] + " ")) + "|");
                if (j == (this.slots[0].length - 1)) {
                    System.out.printf("%n");
                }
            }
            System.out.println("-----------------------------------------");
        }
        System.out.printf("%n");
    }
}
