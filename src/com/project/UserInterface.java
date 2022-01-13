package com.project;

import java.util.Scanner;

public class UserInterface {

    public static final Character SHIP_CHAR = 'N';
    public static final Character KILLED_SHIP_AT_SAME_SLOT_CHAR = 'X';
    public static final Character WRONG_SHOT_AT_SAME_SLOT_CHAR = 'n';
    public static final Character KILLED_SHIP_CHAR = '*';
    public static final Character WRONG_SHOT_CHAR = '-';
    public static final String ALPHABETIC_OPTIONS = "ABCDEFGHIJ";
    private static final String DELIMITER_LINE = "---------------------------------------------\n";

    private static final String VERMELHO = "\033[0;31m";
    private static final String VERDE = "\033[0;32m";
    private static final String AMARELO = "\033[0;33m";
    private static final String LARANJA = "\u001b[38;5;208m";
    private static final String ROXO = "\033[0;34m";
    private static final String ROSA= "\033[0;35m";
    private static final String AZUL = "\033[0;36m";
    private static final String RESET = "\033[0m";
    
    private static final Integer [] POSITIONS = {0,1,2,3,4,5,6,7,8,9};
    private static StringBuilder renderizer = new StringBuilder();

    public static void renderBoard(Object [][] boardToRender,int remainingShips) {
        int atualLap = 0;
        renderRemainingShips(remainingShips);
        renderizer.append("    |");
        renderLine(POSITIONS);
        renderizer.append(DELIMITER_LINE);
        for (Object [] line : boardToRender) {
            renderizer.append(colorizer("| ",AZUL)+ALPHABETIC_OPTIONS.charAt(atualLap)+colorizer(" |",AZUL));
            renderLine(line);
            renderizer.append(DELIMITER_LINE);
            atualLap++;
        }
        System.out.println(renderizer);
        renderizer.delete(0, renderizer.length());
    }

    public static void renderRemainingShips(int remainingShips){
        String s=String.valueOf(remainingShips);
        renderizer.append("\t\t\t");
        renderizer.append("Contador navios:  " + colorizer(s,VERDE) );
        renderizer.append("\n\n");
    }

    private static void renderLine(Object [] line) {
        for (Object slot : line) {
            renderizer.append((slot != null) ? (" "+slot+colorizer(" |",AZUL)) :colorizer("   |",AZUL) );
        }
        renderizer.append("\n");


    }


    private static String colorizer(String toColorize, String color) {
        return color+toColorize+RESET;
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
