package com.codecool.dungeoncrawl.logic.environment;

import com.codecool.dungeoncrawl.logic.Cell;

public class Torch extends Environment {
    public Torch(Cell cell) {
        super(cell);
    }

    public String getTileName() {
        return "torch";
    }
}
