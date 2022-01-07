package com.project;

import java.util.Scanner;

public class GameController {
    private Board playerBoard = new Board();
    private Board cpuBoard = new Board();
    private final int SHIP_AMOUNT = 10;
    private Scanner sc = new Scanner(System.in);

    private void deployPlayerShips () {
        boolean validPosition = true;

        System.out.printf("Escolha as posições do seu navio. EX: 'A0'%n%n");
        for (int i = 0; i < this.SHIP_AMOUNT; i++) {
            do {
                if (!validPosition) System.out.printf("Ops! Posicionamento inválido! Por favor, repita novamente.%n%n");
                this.playerBoard.renderBoard();
                System.out.println("Digite a posicao do navio " + (i + 1));
                validPosition = this.playerBoard.getPosition(this.sc.next());
            }
            while (!validPosition);
        }
    }

    private void runDeployPhase () {
        this.deployPlayerShips();
        //deploy cpu ships
    }

    public void runGame () {
        this.runDeployPhase();
        //start game
    }
}
