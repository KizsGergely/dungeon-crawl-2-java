package com.codecool.dungeoncrawl.logic.environment;

import com.codecool.dungeoncrawl.logic.Cell;

public class Tub extends Environment {
    public Tub(Cell cell) {
        super(cell);
    }

    public String getTileName() {
        return "tub";
    }
}
