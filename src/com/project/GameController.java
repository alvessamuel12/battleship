package com.project;

import java.util.Scanner;

public class GameController {
    private Board playerBoard = new Board();
    private Board cpuBoard = new Board();
    private final int SHIP_AMOUNT = 10;
    private Scanner sc = new Scanner(System.in);

    private void deployPlayerShips () {
        System.out.println("Escolha as posições do seu navio. EX: 'A0'");
        for (int i = 0; i < this.SHIP_AMOUNT; i++) {
            this.playerBoard.renderBoard();
            System.out.println("Digite a posicao do navio " + (i + 1));
            this.playerBoard.getPosition(this.sc.next());
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
