package com.codecool.dungeoncrawl.logic.environment;

import com.codecool.dungeoncrawl.logic.Cell;

public class Fence extends Environment {
    public Fence(Cell cell) {
        super(cell);
    }

    public String getTileName() {
        return "fence";
    }
}
