package com.project;

public class Board {

    public Character[][] slots = new Character[10][10];
    public static final int SHIP_AMOUNT = 5;
    private int remainingShips;

    public Board() {
        this.remainingShips = SHIP_AMOUNT;
    }

    public void addShipAtPosition (Position position) {
        setSlot(UserInterface.SHIP_CHAR, position);
    }


    public int getRemainingShips(){
        return this.remainingShips;
    }

    public void deleteShip(Position position) {
        setSlot(null, position);
        remainingShips--;
    }

    public void deleteShip(Character shipStatus, Position position) {
        setSlot(shipStatus, position);
        remainingShips--;
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
        if (slot != null && (slot.equals('N')
            || slot.equals('n')
            || slot.equals('X')))
            return true;
        return false;
    }
}
