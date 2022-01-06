package com.project;

public class Board {
    private char[][] slots = new char[10][10];

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
    }
}
