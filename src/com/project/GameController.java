package com.project;

import java.util.Scanner;

public class GameController {
    private Board playerBoard = new Board();
    private Board cpuBoard = new Board();
    private Cpu cpu = new Cpu();
    private Scanner sc = new Scanner(System.in);
    private static PlayerTurns playerOfCurrentTurn;

    void turn() {
        switch (playerOfCurrentTurn) {
            case CPU:
                String position = cpu.choosePosition();
                Position convertedPosition = PositionConverter.convertToPosition(position);
                boolean hasShip = playerBoard.hasShip(convertedPosition);
                playerBoard.modifySlot(convertedPosition, hasShip);
                toggle();
                break;
            case PLAYER:
                // TODO criar lógica para turno do player
                break;
        }
        // boolean b = verifyIfWinnerExists();

    }

    private static void toggle() {
        switch (playerOfCurrentTurn) {
            case CPU:
                playerOfCurrentTurn = PlayerTurns.PLAYER;
                break;
            case PLAYER:
                playerOfCurrentTurn = PlayerTurns.CPU;
                break;
        }
    }


    // TODO refatorar metodos deploy para um metodo deploy generico para evitar duplicatas

    private void deployPlayerShips () {
        boolean validPosition = true;

        System.out.printf("Escolha as posições do seu navio. EX: 'A0'%n%n");
        for (int i = 1; i <= Board.SHIP_AMOUNT; i++) {
            String position;
            do {
                if (!validPosition) System.out.printf("Ops! Posicionamento inválido! Por favor, repita novamente.%n%n");
                this.playerBoard.renderBoard();
                System.out.println("Digite a posicao do navio " + i);
                position = this.sc.next();
                validPosition = PositionConverter.validatePosition(position);
            }
            while (!validPosition);
            this.playerBoard.addShipAtPosition(PositionConverter.convertToPosition(position));
        }
    }

    private void deployCpuShips () {
        boolean validPosition = true;

        for (int i = 1; i <= Board.SHIP_AMOUNT; i++) {
            String position;
            do {
                position = this.cpu.choosePosition();
                validPosition = PositionConverter.validatePosition(position);
            }
            while (!validPosition);
            this.cpuBoard.addShipAtPosition(PositionConverter.convertToPosition(position));
        }
    }

    public void toggle(){

    }


    private void runBattlePhase(){
        System.out.println("Inicio do Jogo ");
        while(this.playerBoard.getRemainingShips()>0 && this.cpuBoard.getRemainingShips()>0) {
            this.toggle();
        }
        String winner = (this.playerBoard.getRemainingShips() > 0) ? "O jogador": "O computador" ;
        System.out.println("Fim do Jogo. O vencedor foi " + winner );

    }

    private void runDeployPhase () {
        this.deployPlayerShips();
        this.deployCpuShips();
    }


    public void runGame () {
        this.runDeployPhase();
        //start game
        this.runBattlePhase();

    }
}
