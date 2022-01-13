package com.project;

import java.util.Scanner;

public class GameController {
    private Board playerBoard = new Board();
    private Board cpuBoard = new Board();
    private Cpu cpu = new Cpu();
    private Scanner sc = new Scanner(System.in);
    private static PlayerTurns playerOfCurrentTurn;

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
        
        this.cpuBoard.setSlot(UserInterface.SHIP_CHAR, convertedPosition);
        
        boolean hasShipAtPlayerSlot = playerBoard.hasShip(convertedPosition);
        boolean hasShipAtCPUSlot = cpuBoard.hasShip(convertedPosition);
    
        if (hasShipAtPlayerSlot) {
           
            if (hasShipAtCPUSlot) {
                this.playerBoard.deleteShip(convertedPosition);
                this.cpuBoard.setSlot(UserInterface.KILLED_SHIP_AT_SAME_SLOT_CHAR, convertedPosition);
            } else {
                if (this.playerBoard.getSlot(convertedPosition).equals(UserInterface.KILLED_SHIP_AT_SAME_SLOT_CHAR)) {
                    this.playerBoard.deleteShip(UserInterface.KILLED_SHIP_CHAR, convertedPosition);
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



    private void playerTurn() {
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


    private void runBattlePhase() {
        System.out.println("Inicio do Jogo ");
        
        this.playerOfCurrentTurn = PlayerTurns.PLAYER;
        
        while(this.playerBoard.getRemainingShips()>0 && this.cpuBoard.getRemainingShips()>0) {
            turn();
        }
        String winner = (this.playerBoard.getRemainingShips() > 0) ? "O jogador": "O computador" ;
        System.out.println("\n\nFim do Jogo. O vencedor foi " + winner );

    }

    private void runDeployPhase () {
        this.deployPlayerShips();
        this.deployCpuShips();
    }

    public void runGame () {
        this.runDeployPhase();
        //start game
        this.runBattlePhase();

        // TODO lógica para jogar novamente

    }
}
