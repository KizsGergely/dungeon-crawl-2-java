package com.codecool.dungeoncrawl.logic.environment;

import com.codecool.dungeoncrawl.logic.Cell;

public class Ufo extends Environment {
    public Ufo(Cell cell) {
        super(cell);
    }

    public String getTileName() {
        return "ufo";
    }
}
