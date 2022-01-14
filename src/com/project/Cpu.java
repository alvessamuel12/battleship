package com.project;

import java.util.Arrays;
import java.util.Random;

public class Cpu  {
    private Random random = new Random();
    public Character[][] playerSlots = new Character[10][10];



    public String choosePosition () {
        char row = PositionConverter.convertNumberToLetter(this.random.nextInt(10));
        String column = Integer.toString(this.random.nextInt(10));

        return row + column;
    }


    public String choosePositionCpuWins () {
        char row = ' ';
        String column = "0";

        for (int i =0; i< this.playerSlots.length;i++ ) {
            if (row == ' '){
                for (int j = 0; j< this.playerSlots[i].length;j++){
                    boolean isnull = this.playerSlots[i][j] == null;

                    if (!isnull && (this.playerSlots[i][j] == 'N' || this.playerSlots[i][j] == 'n' || this.playerSlots[i][j] == 'X')){
                        row = PositionConverter.convertNumberToLetter(i);
                        column = Integer.toString(j);
                        this.playerSlots[i][j]=null;
                        break;

                    }

                }

            }
            else{
                break;
            }


        }
        return row + column;


    }
}
