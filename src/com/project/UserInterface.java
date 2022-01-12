package com.project;

public class UserInterface {

    public static final String ALPHABETIC_OPTIONS = "ABCDEFGHIJ";
    private static final Integer [] POSITIONS = {0,1,2,3,4,5,6,7,8,9};
    private static StringBuilder renderizer = new StringBuilder();
    private static final String DELIMITER_LINE = "---------------------------------------------\n";

    public static void renderBoard(Object [][] boardToRender) {
        int atualLap = 0;
        renderizer.append("    |");
        renderLine(POSITIONS);
        renderizer.append(DELIMITER_LINE);
        for (Object [] line : boardToRender) {
            renderizer.append("| "+ALPHABETIC_OPTIONS.charAt(atualLap)+" |");
            renderLine(line);
            renderizer.append(DELIMITER_LINE);
            atualLap++;
        }
        System.out.println(renderizer);
        renderizer.delete(0, renderizer.length());
    }

    private static void renderLine(Object [] line) {
        for (Object slot : line) {
            renderizer.append((slot != null) ? (" "+slot+" |") : "   |");
        }
        renderizer.append("\n");
    }

    
}
