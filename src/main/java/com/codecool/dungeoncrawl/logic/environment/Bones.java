package com.codecool.dungeoncrawl.logic.environment;

import com.codecool.dungeoncrawl.logic.Cell;

public class Bones extends Environment {
    public Bones(Cell cell) {
        super(cell);
    }

    public String getTileName() {
        return "bones";
    }
}
