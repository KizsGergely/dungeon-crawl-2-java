package com.codecool.dungeoncrawl.logic.environment;

import com.codecool.dungeoncrawl.logic.Cell;

public class Bed extends Environment {
    public Bed(Cell cell) {
        super(cell);
    }

    public String getTileName() {
        return "bed";
    }
}
