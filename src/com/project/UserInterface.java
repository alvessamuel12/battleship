package com.project;

import java.util.Scanner;

public class UserInterface {

    public static final Character SHIP_CHAR = 'N';
    public static final Character KILLED_SHIP_AT_SAME_SLOT_CHAR = 'X';
    public static final Character WRONG_SHOT_AT_SAME_SLOT_CHAR = 'n';
    public static final Character KILLED_SHIP_CHAR = '*';
    public static final Character WRONG_SHOT_CHAR = '-';
    public static final String ALPHABETIC_OPTIONS = "ABCDEFGHIJ";
    public static final String DELIMITER_LONG_LINE = "\n\n---------------------------------------------------------\n\n";
    private static final String DELIMITER_BOARD_LINE = "---------------------------------------------\n";

    private static final Integer [] POSITIONS = {0,1,2,3,4,5,6,7,8,9};
    private static StringBuilder renderizer = new StringBuilder();

    public static void renderBoard(Object [][] boardToRender) {
        int atualLap = 0;
        renderizer.append("    |");
        renderLine(POSITIONS);
        renderizer.append(DELIMITER_BOARD_LINE);
        for (Object [] line : boardToRender) {
            renderizer.append("| "+ALPHABETIC_OPTIONS.charAt(atualLap)+" |");
            renderLine(line);
            renderizer.append(DELIMITER_BOARD_LINE);
            atualLap++;
        }
        System.out.println(renderizer);
        renderizer.delete(0, renderizer.length());
    }

    public static String renderRemainingShips(int remainingShips){
        return "\t\t\tContador navios:  " + remainingShips + "\n\n";
    }

    private static void renderLine(Object [] line) {
        for (Object slot : line) {
            renderizer.append((slot != null) ? (" "+slot+" |") : "   |");
        }
        renderizer.append("\n");
    }

    public static String inputPlayerPosition(String message) throws InvalidPositionException {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        String input = in.next();
        if (!PositionConverter.validatePosition(input))
            throw new InvalidPositionException();
        return input;
    }
    
}
