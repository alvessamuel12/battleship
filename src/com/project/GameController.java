package com.project;

import java.util.Scanner;

public class GameController {
    private Board playerBoard = new Board();
    private Board cpuBoard = new Board();
    private Cpu cpu = new Cpu();
    private Scanner sc = new Scanner(System.in);

    private void deployPlayerShips () {
        boolean validPosition = true;

        System.out.printf("Escolha as posições do seu navio. EX: 'A0'%n%n");
        for (int i = 1; i <= this.playerBoard.getSHIP_AMOUNT(); i++) {
            do {
                if (!validPosition) System.out.printf("Ops! Posicionamento inválido! Por favor, repita novamente.%n%n");
                this.playerBoard.renderBoard();
                System.out.println("Digite a posicao do navio " + i);
                validPosition = this.playerBoard.getPosition(this.sc.next());
            }
            while (!validPosition);
        }
    }

    private void deployCpuShips () {
        boolean validPostition = true;

        for (int i = 1; i <= this.cpuBoard.getSHIP_AMOUNT(); i++) {
            do {
                validPostition = this.cpuBoard.getPosition(this.cpu.choosePosition());
            }
            while (!validPostition);
        }
    }

    private void runDeployPhase () {
        this.deployPlayerShips();
        this.deployCpuShips();
    }

    public void runGame () {
        this.runDeployPhase();
        //start game
    }
}
