package com.project;

import java.util.Random;

public class Cpu {
    private Random random = new Random();
    private StringBuilder sb = new StringBuilder();

    public String choosePosition () {
        String row = Integer.toString(this.random.nextInt(10));
        String column = Integer.toString(this.random.nextInt(10));

        return row + column;
    }
}
