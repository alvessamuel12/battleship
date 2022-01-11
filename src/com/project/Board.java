package com.project;

public class Board {

    private Character[][] slots = new Character[10][10];
    public static final int SHIP_AMOUNT = 10;
    private int remainingShips;

    public Board() {
        this.remainingShips = SHIP_AMOUNT;
    }
    
    public void addShipAtPosition (Position position) {
        setSlot('N', position);
    }

    public void modifySlot(Position position, Boolean validator) {
        // TODO lógica para modificar o caractere que representa a posição
    }

    public Character getSlot(Position position) {
        return this.slots[position.getRow()][position.getColumn()];
    }

    public void setSlot(Character shipStatus, Position position) {
        this.slots[position.getRow()][position.getColumn()] = shipStatus;
    }

    public void renderBoard () {
        UserInterface.renderBoard(this.slots);
    }

    public boolean hasShip(Position position) {
        Character slot = getSlot(position);
        if (slot.equals('N')
            || slot.equals('n')
            || slot.equals('X'))
            return true;
        return false;
    }
}
