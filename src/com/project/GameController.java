package com.project;

import java.util.Scanner;

public class GameController {
    private Board playerBoard, cpuBoard = new Board();
    private boolean isPlayerTurn = true;
    private final int SHIP_AMOUNT = 10;
    private Scanner sc = new Scanner(System.in);

    //player deploy

    //cpu deploy
    private void deployPlayerShips () {
        System.out.println("Escolha as posições do seu navio. EX: 'A0'");
        for (int i = 0; i < this.SHIP_AMOUNT; i++) {
            System.out.println("Digite a posicao do navio " + i);
        }
    }
    //ship deploy phase
    private void runDeployPhase () {

    }

    public void runGame () {
        runDeployPhase();
        //start game
    }
}
