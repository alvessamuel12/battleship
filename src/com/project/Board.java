package com.project;

public class Board {
    private Character[][] slots = new Character[10][10];
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

    // "a1".matches("[A-Ja-j]{1}[0-9]{1}");

    private boolean validatePositioning (int row, int column) {
        if (this.slots[row][column] != null) {
            if (row < 0 || row >= this.slots.length || column < 0 || column >= this.slots[0].length) {
                return false;
            }
        }
        return true;  
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
        UserInterface.renderBoard(this.slots);
    }
}
