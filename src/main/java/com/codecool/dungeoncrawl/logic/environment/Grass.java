package com.codecool.dungeoncrawl.logic.environment;

import com.codecool.dungeoncrawl.logic.Cell;

public class Grass extends Environment {
    public Grass(Cell cell) {
        super(cell);
    }

    public String getTileName() {
        return "grass";
    }
}
