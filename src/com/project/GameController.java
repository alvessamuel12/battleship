package com.project;

import java.util.Scanner;

public class GameController {
    private Board playerBoard = new Board();
    private Board cpuBoard = new Board();
    private Cpu cpu = new Cpu();
    private Scanner sc = new Scanner(System.in);
    private static Players playerOfCurrentTurn;

    private void turn() {
        switch (playerOfCurrentTurn) {
            case CPU:
                cpuTurn();
                break;
            case PLAYER:
                playerTurn();
                break;
        }
        toggle();
    }

    private void cpuTurn() {
        String position = cpu.choosePosition();
        Position convertedPosition = PositionConverter.convertToPosition(position);
        boolean hasShipAtPlayerSlot = playerBoard.hasShip(convertedPosition);
        boolean hasShipAtCPUSlot = cpuBoard.hasShip(convertedPosition);
        boolean isNotNull = this.cpuBoard.getSlot(convertedPosition) != null;
        boolean invalidPosition = isNotNull && (this.cpuBoard.getSlot(convertedPosition).equals(UserInterface.KILLED_SHIP_CHAR) ||
                this.cpuBoard.getSlot(convertedPosition).equals(UserInterface.KILLED_SHIP_AT_SAME_SLOT_CHAR));
        if (!invalidPosition) { 
            if (hasShipAtPlayerSlot) {
                if (hasShipAtCPUSlot) {
                    this.playerBoard.deleteShip(convertedPosition);
                    this.cpuBoard.setSlot(UserInterface.KILLED_SHIP_AT_SAME_SLOT_CHAR, convertedPosition);
                } else {
                    if (this.playerBoard.getSlot(convertedPosition).equals(UserInterface.KILLED_SHIP_AT_SAME_SLOT_CHAR)) {
                        this.playerBoard.deleteShip(UserInterface.KILLED_SHIP_CHAR, convertedPosition);
                        this.cpuBoard.setSlot(UserInterface.KILLED_SHIP_CHAR, convertedPosition);
                    } else {
                        this.playerBoard.deleteShip(convertedPosition);
                        this.cpuBoard.setSlot(UserInterface.KILLED_SHIP_CHAR, convertedPosition);
                    }
                }
            } else {
                if (hasShipAtCPUSlot) {
                    this.cpuBoard.setSlot(UserInterface.WRONG_SHOT_AT_SAME_SLOT_CHAR, convertedPosition);
                } else {
                    this.cpuBoard.setSlot(UserInterface.WRONG_SHOT_CHAR, convertedPosition);
                }
            }
        }
    }



    private void playerTurn() {
        System.out.print(UserInterface.renderRemainingShips(this.playerBoard.getRemainingShips()));
        this.playerBoard.renderBoard();

        String position = "";
        do {
            try {
                position = UserInterface.inputPlayerPosition("Digite a posicao que voce deseja atacar: ");
            } catch(Exception e) {
                System.out.println(e.getMessage());
            }
        } while (position.equals(""));
        Position convertedPosition = PositionConverter.convertToPosition(position);
        boolean hasShipAtCPUSlot = cpuBoard.hasShip(convertedPosition);
        boolean hasShipAtPlayerSlot = playerBoard.hasShip(convertedPosition);
        boolean isNotNull = this.playerBoard.getSlot(convertedPosition) != null;
        boolean invalidPosition = isNotNull && (this.playerBoard.getSlot(convertedPosition).equals(UserInterface.KILLED_SHIP_CHAR) ||
                this.playerBoard.getSlot(convertedPosition).equals(UserInterface.KILLED_SHIP_AT_SAME_SLOT_CHAR));
        if (!invalidPosition) {
            if (hasShipAtCPUSlot) {
                if (hasShipAtPlayerSlot) {
                    this.cpuBoard.deleteShip(convertedPosition);
                    this.playerBoard.setSlot(UserInterface.KILLED_SHIP_AT_SAME_SLOT_CHAR, convertedPosition);
                } else {
                    if (this.cpuBoard.getSlot(convertedPosition).equals(UserInterface.KILLED_SHIP_AT_SAME_SLOT_CHAR)) {
                        this.cpuBoard.deleteShip(UserInterface.KILLED_SHIP_CHAR, convertedPosition);
                        this.playerBoard.setSlot(UserInterface.KILLED_SHIP_CHAR, convertedPosition);
                    } else {
                        this.cpuBoard.deleteShip(convertedPosition);
                        this.playerBoard.setSlot(UserInterface.KILLED_SHIP_CHAR, convertedPosition);
                    }
                }

            } else {
                if (hasShipAtPlayerSlot) {
                    this.playerBoard.setSlot(UserInterface.WRONG_SHOT_AT_SAME_SLOT_CHAR, convertedPosition);
                } else {
                    this.playerBoard.setSlot(UserInterface.WRONG_SHOT_CHAR, convertedPosition);
                }
            }
        }
    }

    private static void toggle() {
        switch (playerOfCurrentTurn) {
            case CPU:
                playerOfCurrentTurn = Players.PLAYER;
                break;
            case PLAYER:
                playerOfCurrentTurn = Players.CPU;
                break;
        }
    }

    private void deployPlayerShips () {
        boolean validPosition = true;

        System.out.printf("\n\nEscolha as posições do seu navio. EX: 'A0'%n%n");
        for (int i = 1; i <= Board.SHIP_AMOUNT; i++) {
            String inputPosition;
            Position currentPosition = null;
            do {
                if (!validPosition) System.out.printf("Ops! Posicionamento inválido! Por favor, repita novamente.%n%n");
                this.playerBoard.renderBoard();
                System.out.println("Digite a posicao do navio " + i);
                inputPosition = this.sc.next();
                validPosition = PositionConverter.validatePosition(inputPosition);
                if (validPosition) {
                    currentPosition = PositionConverter.convertToPosition(inputPosition);
                    validPosition = !this.playerBoard.hasShip(currentPosition);
                }
            } while (!validPosition);
            this.playerBoard.addShipAtPosition(currentPosition);
        }
    }

    private void deployCpuShips () {
        boolean validPosition = true;

        for (int i = 0; i < Board.SHIP_AMOUNT; i++) {
            String inputPosition;
            Position currentPosition = null;
            do {
                inputPosition = this.cpu.choosePosition();
                validPosition = PositionConverter.validatePosition(inputPosition);
                if (validPosition) {
                    currentPosition = PositionConverter.convertToPosition(inputPosition);
                    validPosition = !this.cpuBoard.hasShip(currentPosition);
                }
            }
            while (!validPosition);
            this.cpuBoard.addShipAtPosition(currentPosition);
        }
    }


    private void runBattlePhase() {
        System.out.println("Inicio do Jogo ");
        
        this.playerOfCurrentTurn = Players.PLAYER;
        
        while(this.playerBoard.getRemainingShips()>0 && this.cpuBoard.getRemainingShips()>0) {
            turn();
        }
        String winner = (getWinner() == Players.PLAYER) ? "O jogador": "O computador" ;
        System.out.println("\n\n\t\tFim do Jogo. O vencedor foi " + winner +"!");

        System.out.println(UserInterface.DELIMITER_LONG_LINE);
        System.out.println("Imprimindo resultado dos tabuleiros...");
        System.out.println(UserInterface.DELIMITER_LONG_LINE);

        showFinalBoardsResult();
    }

    private Players getWinner() {
        return this.playerBoard.getRemainingShips() > 0 ? Players.PLAYER : Players.CPU;
    }

    private void showFinalBoardsResult() {
        if (getWinner() == Players.PLAYER) {
            showPlayerBoardResult();
            System.out.println(UserInterface.DELIMITER_LONG_LINE);
            showCpuBoardResult();
        } else {
            showCpuBoardResult();
            System.out.println(UserInterface.DELIMITER_LONG_LINE);
            showPlayerBoardResult();
        }
    }

    private void showCpuBoardResult() {
        System.out.println("\t\tTabuleiro do Cpu\n\n");
        this.cpuBoard.renderBoard();
    }

    private void showPlayerBoardResult() {
        System.out.println("\t\tTabuleiro do jogador\n\n");
        this.playerBoard.renderBoard();
    }

    private void runDeployPhase () {
        this.deployPlayerShips();
        this.deployCpuShips();
    }

    private boolean checkPlayAgain (){
        Character input;

        System.out.println("Deseja jogar novamente? s/n");
        input = sc.next().charAt(0);

        if (!input.equals('s') && !input.equals('n')) {
            checkPlayAgain();
        } else {
            if (input == 's') {
                return true;
            }
        }
        return false;
    }

    public void runGame () {
        boolean gameNeedsToBeRepeated;

        this.runDeployPhase();
        this.runBattlePhase();

        gameNeedsToBeRepeated = checkPlayAgain();
        if (gameNeedsToBeRepeated) {
            runGame();
        }
    }
}
