package com.codecool.dungeoncrawl.logic.environment;

import com.codecool.dungeoncrawl.logic.Cell;

public class Beam extends Environment {
    public Beam(Cell cell) {
        super(cell);
    }

    public String getTileName() {
        return "beam";
    }
}
